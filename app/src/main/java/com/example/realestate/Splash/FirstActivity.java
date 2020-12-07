package com.example.realestate.Splash;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Activities.Permisions;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FirstActivity extends AppCompatActivity {
Boolean isRationale;
    Boolean isFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);

                    Intent intent = new Intent(FirstActivity.this, Permisions.class);
                    startActivity(intent);

//                    if (new SharedPreferenceConfig().getBooleanFromSP("isLogin",FirstActivity.this)){
//                        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    else {
//                        Intent intent = new Intent(FirstActivity.this, LoginScreen.class);
//                        startActivity(intent);
//                        finish();}
                       // return;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        thread.start();


    }

}