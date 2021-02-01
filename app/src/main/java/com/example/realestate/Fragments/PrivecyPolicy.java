package com.example.realestate.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.OtherAppointmentAdapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Apoinment.Apointment_Data;
import com.example.realestate.Model.Apoinment.Get_Apointment_Response;
import com.example.realestate.Model.PrivcyPolicyData.PoliciesResponse;
import com.example.realestate.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrivecyPolicy extends Fragment {

    ImageView back_btn;
    ProgressDialog ppProgressdialog;
    TextView tv_heading;

    public PrivecyPolicy() {
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
        View view = inflater.inflate(R.layout.fragment_privecy_policy, container, false);
        back_btn = view.findViewById(R.id.back_btn_privecypolicy);

        tv_heading = view.findViewById(R.id.tv_description);


        ppProgressdialog = new ProgressDialog(getContext());
        ppProgressdialog.setCancelable(false);
        ppProgressdialog.setMessage("Loading ...");

        getprivcypolicydata();
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getprivcypolicydata() {
        ppProgressdialog.show();
        String privacy_policy = "privacy_policy";

        Call<PoliciesResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).PRIVCYPOLICY_CALL(privacy_policy);
        call.enqueue(new Callback<PoliciesResponse>() {
            @Override
            public void onResponse(Call<PoliciesResponse> call, Response<PoliciesResponse> response) {
                if (response.isSuccessful()) {
                    PoliciesResponse policiesResponse = response.body();
                    if (policiesResponse.getMessage().equals("Privacy Policy")) {

                        if (policiesResponse.getData().isEmpty()) {

                        } else {

                            tv_heading.setText(policiesResponse.getData());
                        }


                    } else {

                        Toast.makeText(getContext(), "Null Privacy Policy", Toast.LENGTH_SHORT).show();
                    }

                }
                ppProgressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<PoliciesResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                ppProgressdialog.dismiss();
            }
        });

    }
}