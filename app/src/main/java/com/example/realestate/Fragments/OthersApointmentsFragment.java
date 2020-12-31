package com.example.realestate.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.AppoinmentAdapter;
import com.example.realestate.Adapters.OtherAppointmentAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Apoinment.Apointment_Data;
import com.example.realestate.Model.Apoinment.Apointment_Rply;
import com.example.realestate.Model.Apoinment.Get_Apointment_Response;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OthersApointmentsFragment extends Fragment {

    ImageView backbtn;
    Context context;
    String user_id;
    TextView numofApontment;
    RecyclerView apointmentrecyclerView;
    ProgressDialog myapointmentProgressDialog;
    String appointment_Id = "";
    Button accept, reject, changedate, submitchangedate;
    EditText changedDatetime;

    public OthersApointmentsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_others_apointments, container, false);

        context = this.getContext();


        myapointmentProgressDialog = new ProgressDialog(getContext());
        myapointmentProgressDialog.setMessage("Logining..."); // Setting Message
        myapointmentProgressDialog.setCancelable(false);
        apointmentrecyclerView = view.findViewById(R.id.other_appointments_recycler);
        apointmentrecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());
        putApointmentData(user_id);

        numofApontment = view.findViewById(R.id.numofApontment);
        backbtn = view.findViewById(R.id.back_btn_apointment);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    public void putApointmentData(String user_id) {
        myapointmentProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Get_Apointment_Response> call = retrofit.create(ApiInterface.class).GET_OTHEER_APOINTMENT_CALL(user_id);
        call.enqueue(new Callback<Get_Apointment_Response>() {
            @Override
            public void onResponse(Call<Get_Apointment_Response> call, Response<Get_Apointment_Response> response) {
                if (response.isSuccessful()) {
                    Get_Apointment_Response get_apointment_response = response.body();
                    if (get_apointment_response.getMessage().equals("Appointments for who Uploaded the Property")) {
                        Apointment_Data apointment_data = response.body().getApointment_data();
                        if (apointment_data.getApointmentsArrayList().size() > 0) {
                            numofApontment.setText(apointment_data.getApointmentsArrayList().size() + " Appointments");
                            apointmentrecyclerView.setAdapter(new OtherAppointmentAdapter(getActivity(), context, apointment_data.getApointmentsArrayList(), OthersApointmentsFragment.this));
                        }


                    } else {

                        Toast.makeText(getContext(), "User does not have Any appointment", Toast.LENGTH_SHORT).show();
                    }

                }
                myapointmentProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Get_Apointment_Response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                myapointmentProgressDialog.dismiss();
            }
        });

    }


}