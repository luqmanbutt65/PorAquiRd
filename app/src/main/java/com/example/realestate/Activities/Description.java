package com.example.realestate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.Adapters.FeatureAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Features;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.REST.PropertiesSingle.PropertiesSingleResp;
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
    TextView tv_city, tv_location, tv_price, tv_reviews, description;
    DotsIndicator dotsIndicator;
    ProgressDialog homeProgressDialog;
    RecyclerView recyclerView;
    Button getApointment, getInformation;
    private ArrayList<Properties> propertiesArrayList;
    int propertieID = 1;
    Bundle extras;
    ProgressDialog descriptionProgressDialog;


    ArrayList<PropertiesGallery> propertiesGalleryArrayList;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        // propertieID=getxtr
        extras = getIntent().getExtras();
        if (extras != null) {
            propertieID = extras.getInt("propertieIDKey");
            // and get whatever type user account id is
        }


        descriptionProgressDialog = new ProgressDialog(Description.this);
        descriptionProgressDialog.setMessage("Logining..."); // Setting Message
        descriptionProgressDialog.setCancelable(false);

        getApointment = findViewById(R.id.getapointmentbtn);
        getInformation = findViewById(R.id.info);

        tv_city = findViewById(R.id.city_id);
        tv_location = findViewById(R.id.location_id);
        tv_price = findViewById(R.id.prices);
        tv_reviews = findViewById(R.id.reviews);
        description = findViewById(R.id.tv_description_text);
        recyclerView = findViewById(R.id.featuresrecyclerview);

        description.setMovementMethod(new ScrollingMovementMethod());


        getpropertydata(propertieID);

        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);

        int[] imagge = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house};

        viewPager = (ViewPager) findViewById(R.id.viewpager);



        context = this;
        RecyclerView recyclerView = findViewById(R.id.featuresrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


        String[] description = {"aaaaaa", "sssss", "qqqqqq", "kkk", "kkkkkllll", "ppppppp", "uuuuuuu"};

        int[] image = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house};

        ArrayList<Features> features = new ArrayList<>();

        features.add(new Features(description[0], image[0]));
        features.add(new Features(description[1], image[1]));
        features.add(new Features(description[2], image[2]));

        features.add(new Features(description[3], image[3]));

        features.add(new Features(description[4], image[4]));

        features.add(new Features(description[5], image[5]));

        features.add(new Features(description[6], image[6]));


        recyclerView.setAdapter(new FeatureAdapter(Description.this, context, features));


        getApointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Description.this, BottomsheetApointment.class);
                startActivity(intent);

            }
        });

        getInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public void getpropertydata(int id) {
        descriptionProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<PropertiesSingleResp> call = retrofit.create(ApiInterface.class).PROPERTY_CALL(String.valueOf(id));
        call.enqueue(new Callback<PropertiesSingleResp>() {
            @Override
            public void onResponse(Call<PropertiesSingleResp> call, Response<PropertiesSingleResp> response) {
                if (response.isSuccessful()) {
                    PropertiesSingleResp propertiesSingleResp = response.body();
                    if (propertiesSingleResp.getMessage().equals("single property")) {
                        if (propertiesSingleResp != null) {
                            if (propertiesSingleResp.getPropertiesData() != null) {
                                if (propertiesSingleResp.getPropertiesData().getSingleProperty() != null) {
                                    if(propertiesSingleResp.getPropertiesData().getSingleProperty().getPropertiesGalleryArrayList()!=null){
                                        propertiesGalleryArrayList=propertiesSingleResp.getPropertiesData().getSingleProperty().getPropertiesGalleryArrayList();
                                    }else {
                                        propertiesGalleryArrayList=new ArrayList<>();
                                    }
                                    String city_val = ((city_val = propertiesSingleResp.getPropertiesData().getSingleProperty().getCity()) != null) ? city_val : "N/A";
                                    tv_city.setText(city_val);

                                    String town_val = ((town_val = propertiesSingleResp.getPropertiesData().getSingleProperty().getLocation()) != null) ? town_val : "N/A";
                                    tv_location.setText(town_val);

                                    String review_val = ((review_val = propertiesSingleResp.getPropertiesData().getSingleProperty().getRating()) != null) ? review_val : "N/A";
                                    tv_reviews.setText(review_val);

                                    String price_val = ((price_val = String.valueOf(propertiesSingleResp.getPropertiesData().getSingleProperty().getPrice())) != null) ? price_val : "N/A";
                                    tv_price.setText("$ " + price_val);

                                    String title_val = ((title_val = propertiesSingleResp.getPropertiesData().getSingleProperty().getTitle()) != null) ? title_val : "N/A";
                                    description.setText(title_val);



                                    customViewPager customViewPager = new customViewPager(Description.this, propertiesGalleryArrayList);
                                    viewPager.setAdapter(customViewPager);
                                    dotsIndicator.setViewPager(viewPager);

                                } else {
                                }
                            } else {
                                tv_city.setText("N/A");
                                tv_location.setText("N/A");
                                tv_price.setText("N/A");
                                tv_reviews.setText("N/A");
                                description.setText("N/A");
                            }
                        } else {
                            tv_city.setText("N/A");
                            tv_location.setText("N/A");
                            tv_price.setText("N/A");
                            tv_reviews.setText("N/A");
                            description.setText("N/A");
                        }


                    } else {

                        tv_city.setText("N/A");
                        tv_location.setText("N/A");
                        tv_price.setText("N/A");
                        tv_reviews.setText("N/A");
                        description.setText("N/A");
                    }


                } else {
                    showToast("Error! Please try again!");
                }
                descriptionProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PropertiesSingleResp> call, Throwable t) {
                showToast(t.getMessage());
                descriptionProgressDialog.dismiss();
            }
        });

    }


}