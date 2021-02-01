package com.example.realestate.Fragments;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Activities.Mobile_register_otp;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.AppConstant;
import com.example.realestate.Model.Cell_OTP.Otp_response;
import com.example.realestate.Model.Login;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.NOTIFICATION_SERVICE;


public class ProfileFragment extends Fragment {

    Spinner language, cruncy;
    TextView editprofile, changepassword, myprojects, otpnumber, purchasedplans, mymessages, uploadlimit;
    List<String> listCruncy;
    List<String> listLanguage;
    LinearLayout linearLayout1, linearLayout;
    ImageView back_btn, cancelbtn, upcancel;
    CircleImageView userImage2;
    TextView username, useremail, startplan, endplan, subscribeagain, totallimit, uploadedproperty, subscribeagainplan, connector;
    ProgressDialog profileProgressDialog;
    Button cancel, register;
    Switch notification;
    EditText enternumber;

    //banner ads
    AdView mAdView;


    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


//        mAdView = view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        profileProgressDialog = new ProgressDialog(getContext());
        profileProgressDialog.setMessage("Loading..."); // Setting Message
        profileProgressDialog.setCancelable(false);

        notification = view.findViewById(R.id.notification);

        if (new SharedPreferenceConfig().getBooleanNotificationFromSP("ischecked", getContext())) {
            notification.setChecked(true);
        } else {
            notification.setChecked(false);
        }


        username = view.findViewById(R.id.username);
        useremail = view.findViewById(R.id.useremail);
        purchasedplans = view.findViewById(R.id.purchasedplans);
        mymessages = view.findViewById(R.id.mymessages);
        uploadlimit = view.findViewById(R.id.uploadlimit);
        connector = view.findViewById(R.id.connector);

        language = view.findViewById(R.id.language);
        userImage2 = view.findViewById(R.id.userImage1);
        cruncy = view.findViewById(R.id.cruncy);
        editprofile = view.findViewById(R.id.editprofile);
        changepassword = view.findViewById(R.id.changepassword);
        myprojects = view.findViewById(R.id.myprojects);
        otpnumber = view.findViewById(R.id.otpnumber);
        linearLayout1 = view.findViewById(R.id.linearLayout1);
        linearLayout = view.findViewById(R.id.linearLayout);


//        String expiry_Date = new SharedPreferenceConfig().geteExpiryDateFromSP("expiry", getContext());
//        String upload_limit = new SharedPreferenceConfig().geteUploadlimitFromSP("uploadlimit", getContext());
//
//        uploadlimit.setText("Uplaod Limit: " + upload_limit);
        back_btn = view.findViewById(R.id.back_btn_profile);
        if (GlobalState.getInstance().getUserInfo() != null) {

            String temp_name = GlobalState.getInstance().getUserInfo().getName();
            String temp_email = GlobalState.getInstance().getUserInfo().getEmail();
            username.setText(temp_name);
            useremail.setText(temp_email);

            if (GlobalState.getInstance().getUserInfo().getUpload_limit() != null) {
                String upload_limit = GlobalState.getInstance().getUserInfo().getUpload_limit();
                uploadlimit.setText("Uplaod Limit: " + upload_limit);


            } else {
                uploadlimit.setText("Uplaod Limit: ");
            }


            String path = GlobalState.getInstance().getUserInfo().getUser_image();
            path = AppConstant.IMAGE_PATH_USER + path;

            Glide.with(getContext()).load(path).into(userImage2);

            GlobalState.getInstance().getUserInfo();

            if (GlobalState.getInstance().getUserInfo().getExpiry_date() != null) {

                if (GlobalState.getInstance().getUserInfo().getExpiry_date().isEmpty()) {

                } else {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date expirayDate = null;
                    try {
                        String temExpirayDate = GlobalState.getInstance().getUserInfo().getExpiry_date();
                        //    temExpirayDate="2021-01-19 12:12:12";
                        expirayDate = sdf.parse(temExpirayDate);
                        Log.e("ExpireDate", expirayDate.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Date currentDate = new Date();
                    Log.e("CurrentDate:", expirayDate.toString());


                    if (currentDate.before(expirayDate)) {

                        //TODO: When   current is before expiray
                    } else {
                        //TODO: When Current is after before
                        expirypopup();
                    }

                }

            } else {

            }


            if (GlobalState.getInstance().getUserInfo().getUpload_limit().equals("0")) {
                Uploadlimitpopup();
            }

        } else {


            String path = new SharedPreferenceConfig().geteimageOfUSerFromSP("image", getContext());
            path = AppConstant.IMAGE_PATH_USER + path;

            Glide.with(getContext()).load(path).into(userImage2);


            String temp_name = new SharedPreferenceConfig().getNameOfUSerFromSP("name", getContext());
            String temp_email = new SharedPreferenceConfig().getEmailOfUSerFromSP("email", getContext());
            username.setText(temp_name);
            useremail.setText(temp_email);


        }

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
//
        connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ConnectorProfileFragment_update();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (notification.isChecked()) {
                    notification.setChecked(true);
                    new SharedPreferenceConfig().saveBooleanNotificationInSP("ischecked", true, getContext());

                } else {
                    notification.setChecked(false);
                    new SharedPreferenceConfig().saveBooleanNotificationInSP("ischecked", false, getContext());

                    NotificationManager notify_manager = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
                    notify_manager.cancelAll();
                }
            }
        });
        purchasedplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PurchasedPlans();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        mymessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MyMessages();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        otpnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registernumber();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });


        listCruncy = new ArrayList<String>();
        listCruncy.add("Select A Cruncy Type");
        listCruncy.add("USD");
        listCruncy.add("DOP");
        listCruncy.add("EUR");
        listCruncy.add("JPY");
        //TODOL:Language LIst

        listLanguage = new ArrayList<String>();
        listLanguage.add("Select A Language Type");
        listLanguage.add("US English");
        listLanguage.add("French");
        listLanguage.add("Spanish");
        if (new SharedPreferenceConfig().getBooleanLanguagefrenchFromSP("frenchlanguage", getContext())) {
            listLanguage.add(0, "French");
        } else if (new SharedPreferenceConfig().getBooleanLanguageFromSP("language", getContext())) {
            listLanguage.add(0, "US English");
        } else if (new SharedPreferenceConfig().getBooleanLanguagespanishFromSP("spanishlanguage", getContext())) {
            listLanguage.add(0, "Spanish");
        }
//        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext())) {
//            if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext()) != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", getContext()) != null) {
//                ShowUser(new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext()), new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", getContext()));

//                String path = new SharedPreferenceConfig().geteimageOfUSerFromSP("image", getContext());
//                path = AppConstant.IMAGE_PATH_USER + path;
//
//                Glide.with(getContext()).load(path).into(userImage2);


        useremail.setText(new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext()));
        username.setText(new SharedPreferenceConfig().getNameOfUSerFromSP("name", getContext()));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listLanguage);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapterr = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listCruncy);
        arrayAdapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cruncy.setAdapter(arrayAdapterr);


        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (new SharedPreferenceConfig().getBooleanLanguageFromSP("language", getContext()) ||
                        new SharedPreferenceConfig().getBooleanLanguagefrenchFromSP("frenchlanguage", getContext()) ||
                        new SharedPreferenceConfig().getBooleanLanguagespanishFromSP("spanishlanguage", getContext())) {

                    switch (position) {
                        case 0:

                            break;
                        case 2:

                            new SharedPreferenceConfig().saveBooleanLanguageInSP("language", true, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagefrenchInSP("frenchlanguage", false, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagespanishInSP("spanishlanguage", false, getContext());
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            break;
                        case 3:

                            new SharedPreferenceConfig().saveBooleanLanguageInSP("language", false, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagefrenchInSP("frenchlanguage", true, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagespanishInSP("spanishlanguage", false, getContext());
                            Intent j = new Intent(getActivity(), MainActivity.class);
                            startActivity(j);
                            break;
                        case 4:

                            new SharedPreferenceConfig().saveBooleanLanguageInSP("language", false, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagefrenchInSP("frenchlanguage", false, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagespanishInSP("spanishlanguage", true, getContext());
                            Intent k = new Intent(getActivity(), MainActivity.class);
                            startActivity(k);
                            break;

                    }

                } else {
                    switch (position) {
                        case 0:

                            break;
                        case 1:

                            new SharedPreferenceConfig().saveBooleanLanguageInSP("language", true, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagefrenchInSP("frenchlanguage", false, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagespanishInSP("spanishlanguage", false, getContext());
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            break;
                        case 2:

                            new SharedPreferenceConfig().saveBooleanLanguageInSP("language", false, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagefrenchInSP("frenchlanguage", true, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagespanishInSP("spanishlanguage", false, getContext());
                            Intent j = new Intent(getActivity(), MainActivity.class);
                            startActivity(j);
                            break;
                        case 3:

                            new SharedPreferenceConfig().saveBooleanLanguageInSP("language", false, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagefrenchInSP("frenchlanguage", false, getContext());
                            new SharedPreferenceConfig().saveBooleanLanguagespanishInSP("spanishlanguage", true, getContext());
                            Intent k = new Intent(getActivity(), MainActivity.class);
                            startActivity(k);
                            break;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cruncy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(getContext(), cruncy.getSelectedItem().toString(),
//                                Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProfileFragment_update();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChangePaswsword();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        myprojects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MyProjectsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

//            } else {
//
//
//            }


//        } else {
//            //TODO:User Not Login
//            username.setText("Login First");
//            useremail.setText("Login First");
//            linearLayout1.setVisibility(View.GONE);
//            linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), LoginScreen.class);
//                    startActivity(intent);
//
//                }
//            });
//
//        }


        GlobalState globalState = new GlobalState();


        return view;
    }


    public void registernumber() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.custome_mobile_register, null);

        cancel = dialogView.findViewById(R.id.cancelbuton);
        register = dialogView.findViewById(R.id.registernumber);
        enternumber = dialogView.findViewById(R.id.number);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());
                String mob_number = enternumber.getText().toString().trim();
                if (mob_number == null) {
                    Toast.makeText(getContext(), "Please enter number", Toast.LENGTH_SHORT).show();
                } else {
                    registercell(user_id, mob_number);
                    dialogBuilder.dismiss();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogBuilder.dismiss();

            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }


    public void registercell(String id, String number) {
        profileProgressDialog.show();
        Call<Otp_response> call = ApiClient.getRetrofit().create(ApiInterface.class).OTP_Number_CALL(id, number);
        call.enqueue(new Callback<Otp_response>() {
            @Override
            public void onResponse(Call<Otp_response> call, Response<Otp_response> response) {
                if (response.isSuccessful()) {
                    Otp_response otp_response = response.body();
                    if (otp_response.getMessage().equals("OTP sent to your phone number")) {

                        Toast.makeText(getContext(), "User number  registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), Mobile_register_otp.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(getContext(), "Number not registered", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                profileProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Otp_response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                profileProgressDialog.dismiss();
            }
        });

    }

    public void expirypopup() {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.planexpirypopup, null);


        startplan = dialogView.findViewById(R.id.startplan);
        endplan = dialogView.findViewById(R.id.endplan);
        subscribeagainplan = dialogView.findViewById(R.id.subscribeagainplan);


        cancelbtn = dialogView.findViewById(R.id.epcancel);

        startplan.setText(GlobalState.getInstance().getUserInfo().getPlan_buy_date());
        endplan.setText(GlobalState.getInstance().getUserInfo().getExpiry_date());

        subscribeagainplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new PlanSub();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                dialogBuilder.dismiss();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogBuilder.dismiss();


            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void Uploadlimitpopup() {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.uploadlimitexpirypopup, null);


        totallimit = dialogView.findViewById(R.id.totallimit);
        uploadedproperty = dialogView.findViewById(R.id.uploaded);
        upcancel = dialogView.findViewById(R.id.canceluplimit);
        subscribeagain = dialogView.findViewById(R.id.subscribeagain);
//        totallimit.setText("Total upload limit: 13");
        uploadedproperty.setText("Remaining Upload limit : " + GlobalState.getInstance().getUserInfo().getUpload_limit());

        subscribeagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new PlanSub();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                dialogBuilder.dismiss();
            }
        });
        upcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogBuilder.dismiss();


            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

}