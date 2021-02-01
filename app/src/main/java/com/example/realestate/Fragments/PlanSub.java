package com.example.realestate.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.Adapters.PlanFeatureAdapter;
import com.example.realestate.Adapters.PlandatacontainerAdapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.AppConstant;
import com.example.realestate.Model.Plans.Payment.PaymentResponse;
import com.example.realestate.Model.Plans.Plan;
import com.example.realestate.Model.Plans.PlanFeatures;
import com.example.realestate.Model.Plans.PlanResponse;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.Buttonchecked;
import com.example.realestate.Utills.GlobalState;
import com.example.realestate.Utills.Planclick;
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


public class PlanSub extends Fragment implements Planclick, Buttonchecked {
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
    RecyclerView planfeaturelist;
    RecyclerView planrecyclerView;
    TextView plan_Duration, plan_Price;
    ListView listView;
    ProgressDialog planProgressdilouge;
    ArrayList<PlanFeatures> planFeaturesArrayList0;
    ArrayList<PlanFeatures> planFeaturesArrayList1;
    ArrayList<PlanFeatures> planFeaturesArrayList2;
    //TODO:For PAypal Use!!
    double select_Plan_Amount_USD = 0;
    String select_Plan_Description = "";
    boolean isPlanSelect = false;
    int select_plan_id = 0;
    Button btn_Buy_Now;
    String paypal_id, paypal_state, paypal_amount, paypal_currency_code;
    private PlandatacontainerAdapter plandatacontainerAdapter;
    private ArrayList<Plan> updatedPlanList = new ArrayList<>();


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
        mainlayout = view.findViewById(R.id.rl_one);
        centerimage = view.findViewById(R.id.centerimage);
        plan_Duration = view.findViewById(R.id.duration);
        plan_Price = view.findViewById(R.id.planprice);
        planrecyclerView = view.findViewById(R.id.planrecycler);
        btn_Buy_Now = view.findViewById(R.id.btn_Buy_Now);
        planArrayList = new ArrayList<>();
        planFeaturesArrayList0 = new ArrayList<>();
        planFeaturesArrayList1 = new ArrayList<>();
        planFeaturesArrayList2 = new ArrayList<>();


        planfeaturelist = view.findViewById(R.id.planfeaturelist);

        planfeaturelist.setLayoutManager(new LinearLayoutManager(this.getContext()));

//        chBasic.setChecked(true);
//        chBasic.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red, null));


        planProgressdilouge = new ProgressDialog(getContext());
        planProgressdilouge.setMessage("Loading ...");
        planProgressdilouge.setCancelable(false);
        getPlans();


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
                                updatedPlanList = planArrayList;
                                setrecycle();                                //TODO:For the Paypal USe

                                plan_Duration.setText(planArrayList.get(0).getTitle());
                                plan_Price.setText("$ " + planArrayList.get(0).getPrice());
                                select_Plan_Amount_USD = Double.parseDouble(planArrayList.get(0).getPrice());
                                isPlanSelect = true;
                                select_plan_id = planArrayList.get(0).getId();
                                select_Plan_Description = planArrayList.get(0).getTitle();


//                                if (planArrayList.get(0).getFeaturesArrayList() != null) {
//                                    if (planArrayList.get(0).getFeaturesArrayList().size() > 0) {
//
//                                        planFeaturesArrayList0 = planArrayList.get(0).getFeaturesArrayList();
//                                        if (planFeaturesArrayList0 != null) {
//                                            if (planFeaturesArrayList0.size() > 0) {
//
//                                                updatePlanFeatureList(planFeaturesArrayList0);
//
//                                            }
//                                        }
//                                        //features
//                                        //   showlist(planFeaturesArrayList0);
//
//                                    }
//                                }
                                if (planArrayList.get(1).getFeaturesArrayList() != null) {
                                    if (planArrayList.get(1).getFeaturesArrayList().size() > 0) {

                                        planFeaturesArrayList1 = planArrayList.get(1).getFeaturesArrayList();
                                        //features
//                                        showlist(planFeaturesArrayList1);

                                    }
                                }

                                if (planArrayList.get(2).getFeaturesArrayList() != null) {
                                    if (planArrayList.get(2).getFeaturesArrayList().size() > 0) {

                                        planFeaturesArrayList2 = planArrayList.get(2).getFeaturesArrayList();
                                        //features
//                                        showlist(planFeaturesArrayList2);

                                    }
                                }


                            }

                        }


                    } else {

                        Toast.makeText(getContext(), "Data null", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onclick() {
        Toast.makeText(getContext(), "event clicked", Toast.LENGTH_SHORT).show();
    }

    public void setrecycle() {

        for (int i = 0; i < updatedPlanList.size(); i++) {

            if (i == 0) {
                updatedPlanList.get(i).setIschecked(true);
            } else {
                updatedPlanList.get(i).setIschecked(false);
            }
        }
        plandatacontainerAdapter = new PlandatacontainerAdapter(getActivity(), getContext(), updatedPlanList, this, this);
        planrecyclerView.setAdapter(plandatacontainerAdapter);

    }


    @Override
    public void onPlanCheck(int position) {
        for (int i = 0; i < updatedPlanList.size(); i++) {
            if (i == position) {
                updatedPlanList.get(i).setIschecked(true);
            } else {
                updatedPlanList.get(i).setIschecked(false);
            }
        }
        plandatacontainerAdapter.notifyDataSetChanged();
//        plandatacontainerAdapter=new PlandatacontainerAdapter(getActivity(), getContext(), updatedPlanList, this, this);
//        planrecyclerView.setAdapter(plandatacontainerAdapter);


    }

    @Override
    public void onPlanSelect(int position) {

        Glide.with(this).load(AppConstant.IMAGE_PATH_PLAN + updatedPlanList.get(position).getPlan_image()).into(centerimage);
        plan_Duration.setText(updatedPlanList.get(position).getTitle());
        plan_Price.setText(updatedPlanList.get(position).getPrice());
        ArrayList<PlanFeatures> planFeatures = new ArrayList<>();
        ArrayList<PlanFeatures> emptyplanFeatures = new ArrayList<>();

        planFeatures = updatedPlanList.get(position).getFeaturesArrayList();
        if (planFeatures != null) {
            if (planFeatures.size() > 0) {

                updatePlanFeatureList(planFeatures);
            } else {
                updatePlanFeatureList(emptyplanFeatures);
            }
        } else {
            updatePlanFeatureList(emptyplanFeatures);
        }

    }

    @Override
    public void isPlanSelected(int position) {

        select_plan_id = updatedPlanList.get(position).getId();
        select_Plan_Description = updatedPlanList.get(position).getTitle();
        select_Plan_Amount_USD = Double.parseDouble(updatedPlanList.get(position).getPrice());
        isPlanSelect = true;
    }

    public void updatePlanFeatureList(ArrayList<PlanFeatures> planFeaturesArrayList) {

        planfeaturelist.setAdapter(new PlanFeatureAdapter(getActivity(), getContext(), planFeaturesArrayList));

    }
}