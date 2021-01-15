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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.Connectors_Adapter;
import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Connectors.Connector_response;
import com.example.realestate.Model.Connectors.Connectors;
import com.example.realestate.Model.GetUpdateData.UpdateData_data;
import com.example.realestate.Model.GetUpdateData.UpdateData_response;
import com.example.realestate.Model.GetUpdateData.User;
import com.example.realestate.Model.GetUpdateData.User_Data;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Connector extends Fragment {
    ImageView backbtn_connector;
    RecyclerView connectorRecycler;
    ArrayList<Connectors> connectorsArrayList;
    ProgressDialog connectorprogress;
    TextView totalnum;
    ProgressDialog connectorprogressD;

    public Connector() {
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
        View view = inflater.inflate(R.layout.fragment_connectors, container, false);

        connectorprogressD = new ProgressDialog(getContext());
        connectorprogressD.setMessage("Loading...");
        connectorprogressD.setCancelable(false);
        ConnectorData();
        connectorRecycler = view.findViewById(R.id.connectorRecycler);
        connectorRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        connectorsArrayList = new ArrayList<>();
        backbtn_connector = view.findViewById(R.id.backbtn_connector);
        totalnum = view.findViewById(R.id.totalnum);

        backbtn_connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void ConnectorData() {

        connectorprogressD.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Connector_response> call = retrofit.create(ApiInterface.class).CONNECTOR_CALL();
        call.enqueue(new Callback<Connector_response>() {
            @Override
            public void onResponse(Call<Connector_response> call, Response<Connector_response> response) {
                if (response.isSuccessful()) {
                    Connector_response connector_response = response.body();
                    if (connector_response.getMessage().equals("All Connectors")) {
                        if (connector_response.getData() != null) {
                            if (connector_response.getData().getConnectorsArrayList() != null) {
                                if (connector_response.getData().getConnectorsArrayList().size() > 0) {
                                    connectorsArrayList = connector_response.getData().getConnectorsArrayList();
                                    totalnum.setText(String.valueOf(connector_response.getData().getConnectorsArrayList().size()));
                                    connectorRecycler.setAdapter(new Connectors_Adapter(getActivity(), getContext(), connectorsArrayList));
                                }

                            } else {
                                Toast.makeText(getContext(), "Data is null", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {

                        Toast.makeText(getContext(), "Data fetching Error", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                connectorprogressD.dismiss();
            }

            @Override
            public void onFailure(Call<Connector_response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                connectorprogressD.dismiss();
            }
        });
    }

}