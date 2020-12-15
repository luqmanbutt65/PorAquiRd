package com.example.realestate.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.BottomSheets.BottomSheet;
import com.example.realestate.ChangePaswsword;
import com.example.realestate.Model.Login;
import com.example.realestate.Model.UserInfo;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {

    Spinner language, cruncy;
    TextView editprofile, changepassword, myprojects;
    List<String> listCruncy;
    List<String> listLanguage;
    LinearLayout linearLayout1, linearLayout;
ImageView back_btn;
    TextView username, useremail;
    ProgressDialog profileProgressDialog;
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


        profileProgressDialog = new ProgressDialog(getContext());
        profileProgressDialog.setMessage("Logining..."); // Setting Message
        profileProgressDialog.setCancelable(false);


        username = view.findViewById(R.id.username);
        useremail = view.findViewById(R.id.useremail);
        language = view.findViewById(R.id.language);
        cruncy = view.findViewById(R.id.cruncy);
        editprofile = view.findViewById(R.id.editprofile);
        changepassword = view.findViewById(R.id.changepassword);
        myprojects = view.findViewById(R.id.myprojects);
        linearLayout1 = view.findViewById(R.id.linearLayout1);
        linearLayout = view.findViewById(R.id.linearLayout);

        back_btn=view.findViewById(R.id.back_btn_profile);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),MainActivity.class);
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

        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext())) {
            if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext()) != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", getContext()) != null) {
                ShowUser(new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext()), new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", getContext()));



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
//                        Toast.makeText(getContext(), language.getSelectedItem().toString(),
//                                Toast.LENGTH_SHORT).show();

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

            } else {


            }


        } else {
            //TODO:User Not Login
            username.setText("Login First");
            useremail.setText("Login First");
            linearLayout1.setVisibility(View.GONE);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginScreen.class);
                    startActivity(intent);

                }
            });

        }


        GlobalState globalState = new GlobalState();


        return view;
    }


    public void ShowUser(String email, String pass) {
        profileProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Login> call = retrofit.create(ApiInterface.class).GETPROFILE_CALL(email, pass);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {

                    Login loginresp = response.body();

                    if (loginresp.getMessage().equals("user is logged in")) {

                        String temp_name = loginresp.getUserInfo().getName();
                        String temp_email = loginresp.getUserInfo().getEmail();
                        username.setText(temp_name);
                        useremail.setText(temp_email);
                        GlobalState.getInstance().setUserInfo(loginresp.getUserInfo());

//                        GlobalState.getInstance().getUserInfo();
                    } else {

                        // Toast.makeText(getContext(), "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();

                }

                profileProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                profileProgressDialog.dismiss();
            }
        });


    }


}