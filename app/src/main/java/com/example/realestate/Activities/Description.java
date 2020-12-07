package com.example.realestate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.Adapters.FeatureAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Features;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.R;
import com.example.realestate.Utills.GlobalState;
import com.example.realestate.customViewPager.customViewPager;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Description extends BaseActivity {
Context context;
Features features;
ViewPager viewPager;
DotsIndicator dotsIndicator;
ProgressDialog homeProgressDialog;
    private ArrayList<Properties> propertiesArrayList;
    @Override
    public void onBackPressed() {
     super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        getImages();

        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);

        int[]  imagge={R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house};

        viewPager = (ViewPager)findViewById(R.id.viewpager);

        customViewPager customViewPager= new customViewPager(Description.this, imagge);
        viewPager.setAdapter(customViewPager);
        dotsIndicator.setViewPager(viewPager);

        context=this;
        RecyclerView recyclerView = findViewById(R.id.featuresrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


        String[] description = {"aaaaaa", "sssss", "qqqqqq", "kkk", "kkkkkllll", "ppppppp", "uuuuuuu"};

        int[]  image={R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house};

        ArrayList<Features> features=new ArrayList<>();

        features.add(new Features(description[0],image[0]));
        features.add(new Features(description[1],image[1]));
        features.add(new Features(description[2],image[2]));

        features.add(new Features(description[3],image[3]));

        features.add(new Features(description[4],image[4]));

        features.add(new Features(description[5],image[5]));

        features.add(new Features(description[6],image[6]));


        recyclerView.setAdapter(new FeatureAdapter(Description.this,context,features));




    }

    public void getImages() {
//        homeProgressDialog.show();
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

                                }

                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Data is null", Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        Toast.makeText(getApplicationContext(), "Data fetching error", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
//                homeProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Properties_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                homeProgressDialog.dismiss();
            }
        });

    }




}