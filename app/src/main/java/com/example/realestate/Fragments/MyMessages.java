package com.example.realestate.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.Mymessages_Adapter;
import com.example.realestate.Adapters.Purchasedplan_Adapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Plans.Purchasedplan.PurchasedPlan;
import com.example.realestate.Model.Plans.Purchasedplan.PurchasedPlanResponse;
import com.example.realestate.Model.RejectedAppointmentMessages.RejectedMessages;
import com.example.realestate.Model.RejectedAppointmentMessages.RejectedMessagesResponse;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyMessages extends Fragment {
    RecyclerView mymessagesrecycler;
    ProgressDialog newPrgressdilog;
    ArrayList<RejectedMessages> rejectedMessagesArrayList;
    ImageView backbtn;

    public MyMessages() {
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
        View view = inflater.inflate(R.layout.fragment_my_messages, container, false);

        newPrgressdilog = new ProgressDialog(getContext());
        newPrgressdilog.setMessage("Loading ...");
        newPrgressdilog.setCancelable(false);
        backbtn = view.findViewById(R.id.back_btnRA);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        rejectedMessagesArrayList = new ArrayList<>();
        mymessagesrecycler = view.findViewById(R.id.mymessagesrecycler);
        mymessagesrecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        String user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());

        getPuchasedPlanData(user_id);
        return view;
    }

    public void getPuchasedPlanData(String id) {
        newPrgressdilog.show();

        Call<RejectedMessagesResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).REJECTED_APPOINTMENT_MESSAGES_CALL(id);
        call.enqueue(new Callback<RejectedMessagesResponse>() {
            @Override
            public void onResponse(Call<RejectedMessagesResponse> call, Response<RejectedMessagesResponse> response) {
                if (response.isSuccessful()) {
                    RejectedMessagesResponse rejectedMessagesResponse = response.body();
                    if (rejectedMessagesResponse.getMessage().equals("Rejection Messages")) {


                        if (rejectedMessagesResponse.getData().getRejectedMessagesArrayList() != null) {
                            rejectedMessagesArrayList = rejectedMessagesResponse.getData().getRejectedMessagesArrayList();
                            if (rejectedMessagesArrayList.size() > 0) {

                                mymessagesrecycler.setAdapter(new Mymessages_Adapter(getActivity(), getContext(), rejectedMessagesArrayList));


                            } else {
//                                Toast.makeText(getContext(), "Null Data", Toast.LENGTH_SHORT).show();
                            }
                        } else {
//                            Toast.makeText(getContext(), "Null Data", Toast.LENGTH_SHORT).show();

                        }


                    } else {

                        Toast.makeText(getContext(), "Data null", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                newPrgressdilog.dismiss();
            }

            @Override
            public void onFailure(Call<RejectedMessagesResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                newPrgressdilog.dismiss();
            }
        });

    }

}