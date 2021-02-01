package com.example.realestate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Fragments.ConnectorReviews;
import com.example.realestate.Fragments.Connector_Properties;
import com.example.realestate.Fragments.GoogleMapFragment1;
import com.example.realestate.Model.Connectors.Connectors;
import com.example.realestate.Model.Connectors.connectorData.ConnectorData_Data;
import com.example.realestate.Model.Connectors.connectorData.ConnectorData_response;
import com.example.realestate.Model.GetUpdateData.UpdateData_data;
import com.example.realestate.Model.GetUpdateData.UpdateData_response;
import com.example.realestate.Model.GetUpdateData.User;
import com.example.realestate.Model.GetUpdateData.User_Data;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connector_Description extends BaseActivity {
    TabLayout tab;
    ImageView backbtn_connector;
    CircleImageView connector_photo;
    TextView connector_name, connector_city, connector_contact, connector_email, connector_company, connector_startDate;
    ProgressDialog descriptionDilouge;
    int connectorId;
    Bundle bundle;
    ProgressDialog connectordilouge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connector__description);

        if (isNetworkConnected()) {

        } else {
            networkalert();
        }
        if (new SharedPreferenceConfig().getBooleanLanguageFromSP("language", Connector_Description.this)) {
            setLocale("");
        } else if (new SharedPreferenceConfig().getBooleanLanguagefrenchFromSP("frenchlanguage", Connector_Description.this)) {
            setLocale("es");

        } else if (new SharedPreferenceConfig().getBooleanLanguagespanishFromSP("spanishlanguage", Connector_Description.this)) {
            setLocale("sp");
        }

        backbtn_connector = findViewById(R.id.backbtn_connector);

        connectordilouge = new ProgressDialog(Connector_Description.this);
        connectordilouge.setMessage("Loading..."); // Setting Message
        connectordilouge.setCancelable(false);
        connectordilouge.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Fragment fragment = new ConnectorReviews();
                FragmentManager fragmentManager = Connector_Description.this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.connector_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                connectordilouge.dismiss();
            }
        }, 3000);

        descriptionDilouge = new ProgressDialog(Connector_Description.this);
        descriptionDilouge.setMessage("Loading..."); // Setting Message
        descriptionDilouge.setCancelable(false);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            connectorId = bundle.getInt("connectorid");
            String id = String.valueOf(connectorId);
            new SharedPreferenceConfig().saveconnectorOfUSerInSP("connector_id", id, Connector_Description.this);
//            String userId = new SharedPreferenceConfig().getidOfUSerFromSP("id", Connector_Description.this);
            // and get whatever type user account id is
        }
        connectorProfileData(String.valueOf(connectorId));


        connector_city = findViewById(R.id.connector_city);
        connector_contact = findViewById(R.id.connector_contact);
        connector_email = findViewById(R.id.connectors_email);
        connector_company = findViewById(R.id.connector_company);
        connector_startDate = findViewById(R.id.connector_startdate);
        connector_name = findViewById(R.id.connectors_name);
        connector_photo = findViewById(R.id.connector_photo);


        backbtn_connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Connector_Description.this, MainActivity.class);
                startActivity(i);
            }
        });
        tab = findViewById(R.id.tab_layoutconnectors);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    Fragment fragment = new ConnectorReviews();
                    FragmentManager fragmentManager = Connector_Description.this.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.connector_frame, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                if (tab.getPosition() == 1) {

                    Fragment fragment = new Connector_Properties();
                    FragmentManager fragmentManager = Connector_Description.this.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.connector_frame, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }


            }
        });

    }

    private void connectorProfileData(String id) {

        descriptionDilouge.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<ConnectorData_response> call = retrofit.create(ApiInterface.class).CONNECTORS_DETAIL_CALL(id);
        call.enqueue(new Callback<ConnectorData_response>() {
            @Override
            public void onResponse(Call<ConnectorData_response> call, Response<ConnectorData_response> response) {
                if (response.isSuccessful()) {
                    ConnectorData_response updateData_response = response.body();
                    if (updateData_response.getMessage().equals("Connector Detail")) {
                        ConnectorData_Data connectorData_data = response.body().getData();
                        Connectors connector = response.body().getData().getConnectors();
                        GlobalState.getInstance().setConnectorData_data(connectorData_data);

                        if (connectorData_data != null) {
                            if (connector != null) {
                                connector_name.setText(connector.getName());
                                connector_city.setText(connector.getCity());
                                connector_contact.setText(connector.getPhone_number());
                                connector_email.setText(connector.getEmail());
                                connector_company.setText(connector.getCompany_name());
                                connector_startDate.setText(connector.getCreated_at());

                                if (connector.getUser_image() != null) {
                                    Glide.with(Connector_Description.this).load("https://poraquird.stepinnsolution.com/public/user_images/" + connector.getUser_image()).into(connector_photo);

                                } else {

                                }

                            }

                        } else {
                            showToast("Data is null");
                        }

                    }


                } else {

                    showToast("Error! Please try again!");
                }
                descriptionDilouge.dismiss();
            }

            @Override
            public void onFailure(Call<ConnectorData_response> call, Throwable t) {

                showToast(t.getMessage());
                descriptionDilouge.dismiss();
            }
        });
    }


    @Override
    public void onBackPressed() {

    }
}