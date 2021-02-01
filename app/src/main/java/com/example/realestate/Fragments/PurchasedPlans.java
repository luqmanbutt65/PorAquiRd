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
import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.Adapters.Purchasedplan_Adapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Like.PropertiesLike_Data;
import com.example.realestate.Model.Like.PropertiesLike_Response;
import com.example.realestate.Model.Plans.Purchasedplan.PurchasedPlan;
import com.example.realestate.Model.Plans.Purchasedplan.PurchasedPlanResponse;
import com.example.realestate.Model.Plans.Purchasedplan.PurchasedplanData;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PurchasedPlans extends Fragment {

    RecyclerView purchasedplan;
    ProgressDialog newPrgressdilog;
    ArrayList<PurchasedPlan> purchasedPlanArrayList;
    ImageView backbtn;

    public PurchasedPlans() {
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
        View view = inflater.inflate(R.layout.fragment_purchased_plans, container, false);
        newPrgressdilog = new ProgressDialog(getContext());
        newPrgressdilog.setMessage("Loading ...");
        newPrgressdilog.setCancelable(false);
        backbtn = view.findViewById(R.id.back_btnPL);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        purchasedPlanArrayList = new ArrayList<>();
        purchasedplan = view.findViewById(R.id.purchasedplan);
        purchasedplan.setLayoutManager(new LinearLayoutManager(this.getContext()));
        String user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());

        getPuchasedPlanData(user_id);
        return view;
    }


    public void getPuchasedPlanData(String id) {
        newPrgressdilog.show();

        Call<PurchasedPlanResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).PURCHAASED_PLANDATA_CALL(id);
        call.enqueue(new Callback<PurchasedPlanResponse>() {
            @Override
            public void onResponse(Call<PurchasedPlanResponse> call, Response<PurchasedPlanResponse> response) {
                if (response.isSuccessful()) {
                    PurchasedPlanResponse purchasedPlanResponse = response.body();
                    if (purchasedPlanResponse.getMessage().equals("User Purchases")) {


                        if (purchasedPlanResponse.getData().getPurchasedPlanArrayList() != null) {
                            purchasedPlanArrayList = purchasedPlanResponse.getData().getPurchasedPlanArrayList();
                            if (purchasedPlanArrayList.size() > 0) {

                                purchasedplan.setAdapter(new Purchasedplan_Adapter(getActivity(), getContext(), purchasedPlanArrayList));


                            } else {
                                Toast.makeText(getContext(), "Null Data", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Null Data", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<PurchasedPlanResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                newPrgressdilog.dismiss();
            }
        });

    }
}