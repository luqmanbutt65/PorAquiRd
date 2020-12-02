package com.example.realestate.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.DasboardAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.BottomSheets.BottomSheet;
import com.example.realestate.Model.DashboardData;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.R;

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
    private FrameLayout frameLayout;

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

        context = this.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

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


        String[] city = {"city this is Dummy Data", "city this is Dummy Data", "city this is Dummy Data", "city this is Dummy Data", "city this is Dummy Data", "city this is Dummy Data", "city this is Dummy Data"};
        String[] location = {"Location this is Dummy Data", "Location this is Dummy Data", "Location this is Dummy Data", "Location this is Dummy Data", "Location this is Dummy Data", "Location this is Dummy Data", "Location this is Dummy Data"};
        double[] rating = {121, 121, 12121, 1212, 12121, 21212, 1212};
        double[] price = {2323, 32323, 32232, 23232, 2323223, 2323, 2332};
        String[] title = {"Title this is Dummy Data", "Title this is Dummy Data", "Title this is Dummy Data", "Title this is Dummy Data", "Title this is Dummy Data", "Title this is Dummy Data", "Title this is Dummy Data"};

        String[] bedroom = {"01 bedroom", "01 bedroom", "01 bedroom", "01 bedroom", "01 bedroom", "01 bedroom", "01 bedroom"};
        String[] bath = {"02 bath", "02 bath", "02 bath", "02 bath", "02 bath", "02 bath", "02 bath"};
        String[] area = {"123m", "123m", "123m", "123m", "123m", "123m", "123m"};


        int[] image = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house};
        ArrayList<Properties> properties = new ArrayList<>();
//        properties.add(new Properties(city[0], location[0], rating[0], price[0], title[0],image[0],bedroom[0],bath[0],area[0]));
//        properties.add(new Properties(city[1], location[1], rating[1], price[1], title[1], image[1],bedroom[1],bath[1],area[1]));
//        properties.add(new Properties(city[2], location[2], rating[2], price[2], title[2], image[2],bedroom[2],bath[2],area[2]));
//
//        properties.add(new Properties(city[3], location[3], rating[3], price[3], title[3], image[3],bedroom[3],bath[3],area[3]));
//
//        properties.add(new Properties(city[4], location[4], rating[4], price[4], title[4], image[4],bedroom[4],bath[4],area[4]));
//
//        properties.add(new Properties(city[5], location[5], rating[5], price[5], title[5], image[5],bedroom[5],bath[5],area[5]));
//
//        properties.add(new Properties(city[6], location[6], rating[6], price[6], title[6], image[6],bedroom[6],bath[6],area[6]));


        recyclerView.setAdapter(new DasboardAdapter(getActivity(), context, properties));

        return view;
    }




    public void getData() {
//        otpProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Properties_Response> call = retrofit.create(ApiInterface.class).DASHBOARDDATA_CALL();
        call.enqueue(new Callback<Properties_Response>() {
            @Override
            public void onResponse(Call<Properties_Response> call, Response<Properties_Response> response) {
                if (response.isSuccessful()) {
                    Properties_Response properties_response = response.body();
                    if (properties_response.getMessage().equals("all properties")) {

                        Properties_Data properties_data=response.body().getData();


                        if (properties_data!= null){




                        }else {
                            Toast.makeText(getContext(),"Data is null",Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        Toast.makeText(getContext(), "Data fetching error", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
//                otpProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Properties_Response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                otpProgressDialog.dismiss();
            }
        });

    }
}