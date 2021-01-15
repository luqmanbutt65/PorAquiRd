package com.example.realestate.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.realestate.Adapters.ConnectorPropertyAdapter;
import com.example.realestate.Adapters.ConnectorReviewAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Connectors.connectorData.ConnectorData_Data;
import com.example.realestate.Model.Like.PropertiesLike_Data;
import com.example.realestate.Model.Like.PropertiesLike_Response;
import com.example.realestate.R;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Connector_Properties extends Fragment {

    RecyclerView connectorpropertyrecycler;
    ArrayList<com.example.realestate.Model.Connectors.connectorData.Connector_Properties> connector_propertiesArrayList;

    public Connector_Properties() {
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
        View view = inflater.inflate(R.layout.fragment_connector__properties, container, false);

        connector_propertiesArrayList = new ArrayList<>();
        connectorpropertyrecycler = view.findViewById(R.id.connector_property);
        connectorpropertyrecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));


        ConnectorData_Data connectorData_data = GlobalState.getInstance().getConnectorData_data();

        if (connectorData_data != null) {
            connector_propertiesArrayList = connectorData_data.getConnector_propertiesArrayList();

            if (connector_propertiesArrayList != null) {
                if (connector_propertiesArrayList.size() > 0) {
//                    numofcomments.setText(connectorReviewsArrayList.size() + " Reviews");
                    connectorpropertyrecycler.setAdapter(new ConnectorPropertyAdapter(getActivity(), getContext(), connector_propertiesArrayList));
                }
            } else {
                Toast.makeText(getContext(), "array null", Toast.LENGTH_SHORT).show();
            }
        }
        return view;
    }

//    public void getConnectorPropertyData(String id) {
////        homeProgressDialog.show();
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        Call<PropertiesLike_Response> call = retrofit.create(ApiInterface.class).GETPROPERTIES_AGENT_CALL(id);
//        call.enqueue(new Callback<PropertiesLike_Response>() {
//            @Override
//            public void onResponse(Call<PropertiesLike_Response> call, Response<PropertiesLike_Response> response) {
//                if (response.isSuccessful()) {
//                    PropertiesLike_Response propertiesLike_response = response.body();
//                    if (propertiesLike_response.getMessage().equals("user fav properties")) {
//
//
//
//                    } else {
//
//                        Toast.makeText(getContext(), "Data fetching error", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                } else {
//
//                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
//                }
////                homeProgressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<PropertiesLike_Response> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
////                homeProgressDialog.dismiss();
//            }
//        });
//
//    }
}