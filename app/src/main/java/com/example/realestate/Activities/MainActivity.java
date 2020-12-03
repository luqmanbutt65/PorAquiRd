package com.example.realestate.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Fragments.MyApointmentsFragment;
import com.example.realestate.Fragments.ProfileFragment;
import com.example.realestate.Fragments.Homefragment;
import com.example.realestate.Fragments.MapsFragment;
import com.example.realestate.Fragments.MyProjectsFragment;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.R;
import com.example.realestate.Registration.OTPScreenResetPass;
import com.example.realestate.Registration.resetpassword;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {
    ImageView cancelbtn,drawerbtn;
    Button menu,feed, privcypolicy, termcondition, logout;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;



    @Override
    public void onBackPressed() {
       // super.onBackPressed();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new Homefragment()).commit();
        drawerbtn = findViewById(R.id.drawer);
        menu = findViewById(R.id.menu);
        feed = findViewById(R.id.feeds);
        privcypolicy = findViewById(R.id.privacypolicy);
        termcondition = findViewById(R.id.termcondition);
        cancelbtn = findViewById(R.id.cancel_button);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomappbar);
        logout = findViewById(R.id.logout);

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout=findViewById(R.id.drawerlayout);
                drawerLayout.closeDrawer(Gravity.LEFT,false);
            }
        });

        //BottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp=null;
                switch (item.getItemId()) {
                    case R.id.home: temp=new Homefragment();

                        break;
                    case R.id.location: temp=new MapsFragment();

                        break;
                    case R.id.likes:temp=new MyProjectsFragment();

                        break;
                    case R.id.booking:temp=new MyApointmentsFragment();
                        break;
                    case R.id.profile:temp=new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
                return true;
            }
        });
    }

    public  void onclick(View v){
        if (v.getId()==R.id.menu){


        }

        if (v.getId()==R.id.feeds){


        }
        if (v.getId()==R.id.privacypolicy){


        }
        if (v.getId()==R.id.termcondition){


        }
        if (v.getId()==R.id.logout){


        }

    }





}