package com.example.realestate.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.realestate.Fragments.MyApointmentsFragment;
import com.example.realestate.Fragments.ProfileFragment;
import com.example.realestate.Fragments.Homefragment;
import com.example.realestate.Fragments.MapsFragment;
import com.example.realestate.Fragments.MyProjectsFragment;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView cancelbtn,drawerbtn;
    Button menu,feed, privcypolicy, termcondition, logout;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;

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
}