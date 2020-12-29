package com.example.realestate.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Fragments.Apointments_Tab;
import com.example.realestate.Fragments.MyApointmentsFragment;
import com.example.realestate.Fragments.MyFavrotFragment;
import com.example.realestate.Fragments.PrivecyPolicy;
import com.example.realestate.Fragments.ProfileFragment;
import com.example.realestate.Fragments.Homefragment;
import com.example.realestate.Fragments.MapsFragment;
import com.example.realestate.Fragments.MyProjectsFragment;
import com.example.realestate.Fragments.TermConditions;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.Registration.OTPScreenResetPass;
import com.example.realestate.Registration.resetpassword;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    Fragment temp;

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new Homefragment()).commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomappbar);


        //BottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        temp = new Homefragment();
                        callFreg(temp);


                        break;
                    case R.id.location:
                        temp = new MapsFragment();
                        callFreg(temp);

                        break;
                    case R.id.likes:


                        String user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", MainActivity.this);
                        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", MainActivity.this)) {
                            if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", MainActivity.this)
                                    != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", MainActivity.this) != null) {
                                temp = new MyFavrotFragment();
                                callFreg(temp);
                            }
                        } else {
                            showToast("You are Not Logged in");
                        }

                        break;
                    case R.id.booking:
                        temp = new Apointments_Tab();
                        callFreg(temp);

                        break;


                    case R.id.profile:
                        if (new SharedPreferenceConfig().getBooleanFromSP(Common.ISLOGIN, MainActivity.this)) {
                            temp = new ProfileFragment();
                            callFreg(temp);

                        } else {

                            dilougeEnterBedroom();
                        }


                        break;
                }
                return true;
            }
        });

    }

    private void callFreg(Fragment temp1) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, temp1).commit();

    }

    public void dilougeEnterBedroom() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(MainActivity.this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.loginpopup, null);


        RelativeLayout cancel = dialogView.findViewById(R.id.cancel);
        RelativeLayout login = dialogView.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                startActivity(intent);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

}