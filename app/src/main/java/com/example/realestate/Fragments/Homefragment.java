package com.example.realestate.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.BottomSheets.BottomSheet;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Homefragment extends Fragment {
    ImageView drawerbtn, filter, notification;
    Context context;
    EditText search;
    DrawerLayout drawerLayout;
    DashBoardAdapter dashBoardAdapter;
    RecyclerView homeRecylerView;
    TextView tv_result_number;
    private FrameLayout frameLayout;
    private ArrayList<Properties> propertiesArrayList;
    ProgressDialog homeProgressDialog;

    public Homefragment() {
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
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);
        tv_result_number = view.findViewById(R.id.tv_result_number);
        propertiesArrayList = new ArrayList<>();

        homeProgressDialog = new ProgressDialog(getContext());
        homeProgressDialog.setMessage("Logining..."); // Setting Message
        homeProgressDialog.setCancelable(false);

        context = this.getContext();
        homeRecylerView = view.findViewById(R.id.homeRecylerView);
        homeRecylerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        notification = view.findViewById(R.id.notification);
        filter = view.findViewById(R.id.filter);
        search = view.findViewById(R.id.search);
        drawerbtn = view.findViewById(R.id.drawer);

        drawerLayout = view.findViewById(R.id.drawerlayout);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BottomSheet();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

//        drawerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout = view.findViewById(R.id.drawerlayout);
//                drawerLayout.openDrawer(Gravity.LEFT, true);
//            }
//        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new Notifications(this);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        //TODO: CAll the Api for Get the List of All Avaiable Properties Houses
        getData();
        return view;
    }


    public void getData() {
        homeProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Properties_Response> call = retrofit.create(ApiInterface.class).DASHBOARDDATA_CALL();
        call.enqueue(new Callback<Properties_Response>() {
            @Override
            public void onResponse(Call<Properties_Response> call, Response<Properties_Response> response) {
                if (response.isSuccessful()) {
                    Properties_Response properties_response = response.body();
                    if (properties_response.getMessage().equals("all properties")) {

                        Properties_Data properties_data = response.body().getData();


                        if (properties_data != null) {
                            if (properties_data.getPropertiesArrayList() != null) {
                                propertiesArrayList = properties_data.getPropertiesArrayList();
                                GlobalState.getInstance().setPropertiesArrayList(propertiesArrayList);


                                ArrayList<Properties> tempTestList=GlobalState.getInstance().getPropertiesArrayList();

                                if (propertiesArrayList.size() > 0) {
                                    tv_result_number.setText(String.valueOf(propertiesArrayList.size()));
                                    homeRecylerView.setAdapter(new DashBoardAdapter(getActivity(), context, propertiesArrayList));

                                }

                            }

                        } else {
                            Toast.makeText(getContext(), "Data is null", Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        Toast.makeText(getContext(), "Data fetching error", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                homeProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Properties_Response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                homeProgressDialog.dismiss();
            }
        });

    }
}