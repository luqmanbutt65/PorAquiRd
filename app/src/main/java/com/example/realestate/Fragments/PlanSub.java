package com.example.realestate.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Plans.Payment.PaymentResponse;
import com.example.realestate.Model.Plans.Plan;
import com.example.realestate.Model.Plans.PlanFeatures;
import com.example.realestate.Model.Plans.PlanResponse;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlanSub extends Fragment {
    public static String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    // note that these credentials will differ between live & sandbox environments.
    public static String CONFIG_CLIENT_ID = "AV85peUvVPN7YLWlb6IgxGswccH_opVVO0n-hunJbVm45xvxLpr0xcuzNPjO_5xuc4pENphXC3Yripfd";      /// add your paypal client id
    public static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID);
    private static int REQUEST_CODE_PAYMENT = 1;
    public ArrayList<Plan> planArrayList;
    ImageView backbtn, centerimage;
    CheckBox chBasic, chPrimary, chPremier;
    RelativeLayout mainlayout;
    TextView plan_Duration, plan_Price;
    ListView listView;
    ProgressDialog planProgressdilouge;
    ArrayList<PlanFeatures> planFeaturesArrayList;
    //TODO:For PAypal Use!!
    double select_Plan_Amount_USD = 0;
    String select_Plan_Description = "";
    boolean isPlanSelect = false;
    int select_plan_id = 0;
    Button btn_Buy_Now;
    String paypal_id, paypal_state, paypal_amount, paypal_currency_code;

    public PlanSub() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan_sub, container, false);
        backbtn = view.findViewById(R.id.planback);
        chBasic = view.findViewById(R.id.basic);
        chPrimary = view.findViewById(R.id.primary);
        chPremier = view.findViewById(R.id.premier);
        mainlayout = view.findViewById(R.id.rl_one);
        centerimage = view.findViewById(R.id.centerimage);
        plan_Duration = view.findViewById(R.id.duration);
        plan_Price = view.findViewById(R.id.planprice);
        listView = view.findViewById(R.id.planfeaturelist);
        btn_Buy_Now = view.findViewById(R.id.btn_Buy_Now);
        planArrayList = new ArrayList<>();
        planFeaturesArrayList = new ArrayList<>();

        chBasic.setChecked(true);
        chBasic.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red, null));


        planProgressdilouge = new ProgressDialog(getContext());
        planProgressdilouge.setMessage("Loading ...");
        planProgressdilouge.setCancelable(false);
        getPlans();
        checklistner(chBasic,
                chPrimary,
                chPremier,
                mainlayout,
                "#DF2D2D",
                ResourcesCompat.getDrawable(getResources(), R.drawable.red, null),
                centerimage,
                ResourcesCompat.getDrawable(getResources(), R.drawable.basic, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.bgcheckbox, null));

        checklistner(chPrimary,
                chBasic, chPremier,
                mainlayout,
                "#FEB63D",
                ResourcesCompat.getDrawable(getResources(), R.drawable.yellow, null),
                centerimage,
                ResourcesCompat.getDrawable(getResources(), R.drawable.primary, null), ResourcesCompat.getDrawable(getResources(), R.drawable.bgcheckbox, null));
        checklistner(chPremier,
                chBasic,
                chPrimary,
                mainlayout,
                "#2FB4FF",
                ResourcesCompat.getDrawable(getResources(), R.drawable.sky, null),
                centerimage,
                ResourcesCompat.getDrawable(getResources(), R.drawable.premier, null), ResourcesCompat.getDrawable(getResources(), R.drawable.bgcheckbox, null));

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        btn_Buy_Now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLogin = false;
                isLogin = new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext());

                if (isPlanSelect && isLogin) {
                    onBuyPressed();

                } else {
                    Intent intent = new Intent(getActivity(), LoginScreen.class);
                    startActivity(intent);

                }
            }
        });
        return view;
    }

    public void onBuyPressed() {
        PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }

    private PayPalPayment getThingToBuy(String paymentIntent) {
        return new PayPalPayment(new BigDecimal(select_Plan_Amount_USD), "USD", select_Plan_Description,
                paymentIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        Log.e("Show", confirm.toJSONObject().toString(4));
                        Log.e("Show", confirm.getPayment().toJSONObject().toString(4));

                        JSONObject json = confirm.toJSONObject();
                        JSONObject responce = json.getJSONObject("response");
                        paypal_id = responce.getString("id");
                        paypal_state = responce.getString("state");
                        JSONObject payment = confirm.getPayment().toJSONObject();
                        paypal_amount = payment.getString("amount");
                        paypal_currency_code = payment.getString("currency_code");
                        String user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());
                        sendSuuceesToServer(user_id, paypal_id, paypal_amount, paypal_currency_code, paypal_state, responce);

                        // Toast.makeText(getApplicationContext(), "PaymentConfirmation info received" + " from PayPal", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        // Toast.makeText(getApplicationContext(), "an extremely unlikely failure" +
                        //       " occurred:", Toast.LENGTH_LONG).show();
                        showToast("an extremely unlikely failure occurred");
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {

            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                // Toast.makeText(getApplicationContext(), "An invalid Payment or PayPalConfiguration" +
                //       " was submitted. Please see the docs.", Toast.LENGTH_LONG).show();
                showToast("An invalid Payment or PayPalConfiguration");
            }
        }
    }

    private void sendSuuceesToServer(String user_id1, String paypal_id, String paypal_amount, String paypal_currency_code, String paypal_state, JSONObject responce) {


        planProgressdilouge.show();

        Call<PaymentResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).PLAN_SUCCESS__CALL(String.valueOf(user_id1), paypal_id, String.valueOf(select_plan_id), String.valueOf(select_Plan_Amount_USD));
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                if (response.isSuccessful()) {
                    PaymentResponse paymentResponse = response.body();
                    if (paymentResponse.getMessage().equals("Payment Saved Successful")) {
                        Toast.makeText(getContext(), "Payment Done successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Error Please try again", Toast.LENGTH_SHORT).show();
                    }


                }
                planProgressdilouge.dismiss();
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                planProgressdilouge.dismiss();
            }
        });

    }


    //Click Listner For All CheckBox
    public void checklistner(CheckBox ch, CheckBox ch1, CheckBox ch2, RelativeLayout rl, String color, Drawable drawable, ImageView imageView, Drawable centerimage,
                             Drawable drawablenull) {
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (ch.isChecked()) {
                    ch1.setChecked(false);
                    ch2.setChecked(false);
                    ch.setChecked(true);
                    ch.setBackground(drawable);
                    ch1.setBackground(drawablenull);
                    ch2.setBackground(drawablenull);
                    rl.setBackgroundColor(Color.parseColor(color));
                    imageView.setImageDrawable(centerimage);
                }

                if (getView().findViewById(R.id.basic).equals(ch)) {
                    //  showToast("basic");
                    plan_Duration.setText(planArrayList.get(0).getTitle());
                    plan_Price.setText("$" + planArrayList.get(0).getPrice());
                    select_Plan_Amount_USD = Double.parseDouble(planArrayList.get(0).getPrice());
                    isPlanSelect = true;
                    select_plan_id = planArrayList.get(0).getId();
                    select_Plan_Description = planArrayList.get(0).getTitle();

                } else if (getView().findViewById(R.id.primary).equals(ch)) {
                    //  showToast("primary");
                    plan_Duration.setText(planArrayList.get(1).getTitle());
                    plan_Price.setText("$" + planArrayList.get(1).getPrice());
                    select_Plan_Amount_USD = Double.parseDouble(planArrayList.get(1).getPrice());
                    isPlanSelect = true;
                    select_plan_id = planArrayList.get(1).getId();
                    select_Plan_Description = planArrayList.get(1).getTitle();
                } else if (getView().findViewById(R.id.premier).equals(ch)) {
                    // showToast("premier");
                    plan_Duration.setText(planArrayList.get(2).getTitle());
                    plan_Price.setText("$" + planArrayList.get(2).getPrice());
                    select_Plan_Amount_USD = Double.parseDouble(planArrayList.get(2).getPrice());
                    isPlanSelect = true;
                    select_plan_id = planArrayList.get(2).getId();
                    select_Plan_Description = planArrayList.get(2).getTitle();
                }
            }
        });

    }

    //TODO: Get Plan List From Server
    public void getPlans() {
        planProgressdilouge.show();

        Call<PlanResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).PLAN_DATA_CALL();
        call.enqueue(new Callback<PlanResponse>() {
            @Override
            public void onResponse(Call<PlanResponse> call, Response<PlanResponse> response) {
                if (response.isSuccessful()) {
                    PlanResponse planResponse = response.body();
                    if (planResponse.getMessage().equals("Payment Saved Successful")) {

                        planArrayList = planResponse.getData();
                        if (planArrayList != null) {
                            if (planArrayList.size() > 0) {
                                planArrayList = planResponse.getData();
                                //Toast.makeText(getContext(), "Api hit", Toast.LENGTH_SHORT).show();

                                //TODO:For the Paypal USe
                                plan_Duration.setText(planArrayList.get(0).getTitle());
                                plan_Price.setText("$" + planArrayList.get(0).getPrice());
                                select_Plan_Amount_USD = Double.parseDouble(planArrayList.get(0).getPrice());
                                isPlanSelect = true;
                                select_plan_id = planArrayList.get(0).getId();
                                select_Plan_Description = planArrayList.get(0).getTitle();


                                for (int i = 0; i < planArrayList.size(); i++) {
                                    if (planArrayList.get(i).getFeaturesArrayList() != null) {
                                        if (planArrayList.get(i).getFeaturesArrayList().size() > 0) {
                                            for (int j = 0; j < planFeaturesArrayList.size(); j++) {
                                                planFeaturesArrayList = planArrayList.get(i).getFeaturesArrayList();
                                                //features
//                                            ArrayAdapter adapter = new ArrayAdapter<PlanFeatures>(getContext(), R.layout.activity_listview, planFeaturesArrayList);
//                                            listView.setAdapter(adapter);
                                            }
                                        }
                                    }
                                }


                            }

                        }


                    } else {

                        Toast.makeText(getContext(), "Data fetching error", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                planProgressdilouge.dismiss();
            }

            @Override
            public void onFailure(Call<PlanResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                planProgressdilouge.dismiss();
            }
        });

    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


}