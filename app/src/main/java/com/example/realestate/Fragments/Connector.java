package com.example.realestate.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    ImageView filter_connector;
    ProgressDialog connectorprogressD;
    EditText search;
    LinearLayout linearLayout;
    CheckBox city, cell, ranking;

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
        city = view.findViewById(R.id.city);
        cell = view.findViewById(R.id.cell);
        ranking = view.findViewById(R.id.ranking);
        linearLayout = view.findViewById(R.id.searchparam);
        filter_connector = view.findViewById(R.id.filter_connector);
        filter_connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        search = view.findViewById(R.id.searchconnector);

        backbtn_connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        searchby(city, cell, ranking, "city");
        searchby(cell, city, ranking, "cell");
        searchby(ranking, cell, city, "ranking");
        if (city.isChecked() || cell.isChecked() || ranking.isChecked()) {

        } else {
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                    ArrayList<Connectors> properties1 = new ArrayList<>();
                    if (s.length() > 1) {
                        for (Connectors x : connectorsArrayList) {
                            if (x.getName() != null || x.getEmail() != null) {
                                if (x.getName().toLowerCase().contains(s) && x.getEmail().toLowerCase().contains(s)) {
                                    properties1.add(x);
                                    Log.e("size", "resulttxtchnge" + x.getName());

                                }

                            }
                        }

                        connectorRecycler.setAdapter(new Connectors_Adapter(getActivity(), getContext(), properties1));
                        totalnum.setText(String.valueOf(properties1.size()));


                    }

                    if (s.length() == 0) {

                        connectorRecycler.setAdapter(new Connectors_Adapter(getActivity(), getContext(), connectorsArrayList));
                        totalnum.setText(String.valueOf(connectorsArrayList.size()));


                    }
                }

                @Override
                public void afterTextChanged(Editable s) {


                }
            });
        }


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

                        Toast.makeText(getContext(), "Data Null", Toast.LENGTH_SHORT).show();
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

    public void searchby(CheckBox checkbox, CheckBox checkbox1, CheckBox checkbox2, String name) {


        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkbox.isChecked()) {
                    checkbox1.setChecked(false);
                    checkbox2.setChecked(false);
                    checkbox.setChecked(true);
                    search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {


                            ArrayList<Connectors> properties1 = new ArrayList<>();
                            if (s.length() > 0) {
                                for (Connectors x : connectorsArrayList) {
                                    if (x.getName() != null || x.getEmail() != null) {
                                        if (name.equals("city")) {
                                            if (x.getCity() != null) {
                                                if (x.getCity().toLowerCase().contains(s)) {
                                                    properties1.add(x);
                                                    Log.e("size", "resulttxtchnge" + x.getName());

                                                }
                                            } else {
                                            }
                                        } else if (name.equals("ranking")) {

                                            if (x.getRating() != null) {

                                                if (x.getRating().toLowerCase().contains(s)) {
                                                    properties1.add(x);
                                                    Log.e("size", "resulttxtchnge" + x.getName());

                                                }
                                            } else {
                                            }

                                        } else if (name.equals("cell")) {

                                            if (x.getNumber() != null) {
                                                if (x.getNumber().toLowerCase().contains(s)) {
                                                    properties1.add(x);
                                                    Log.e("size", "resulttxtchnge" + x.getName());

                                                }
                                            } else {
                                            }
                                        }

                                    }
                                }

                                connectorRecycler.setAdapter(new Connectors_Adapter(getActivity(), getContext(), properties1));
                                totalnum.setText(String.valueOf(properties1.size()));


                            }

                            if (s.length() == 0) {

                                connectorRecycler.setAdapter(new Connectors_Adapter(getActivity(), getContext(), connectorsArrayList));
                                totalnum.setText(String.valueOf(connectorsArrayList.size()));


                            }

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });


    }
}