package com.example.realestate.Registration;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.realestate.Activities.BaseActivity;
import com.example.realestate.Activities.MainActivity;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Login;
import com.example.realestate.Model.UserInfo;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginScreen extends BaseActivity {
    EditText emailLogin, passwordLogin;
    Button relativeLayout;
    ProgressDialog loginProgressDialog;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        relativeLayout = findViewById(R.id.letTheUserLogIn);

        loginProgressDialog = new ProgressDialog(LoginScreen.this);
        loginProgressDialog.setMessage("Logining..."); // Setting Message
        loginProgressDialog.setCancelable(false);


//        progressbar = findViewById(R.id.progress);
        String getEmail = emailLogin.getText().toString().trim();
        String getPassword = passwordLogin.getText().toString().trim();

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getEmail = emailLogin.getText().toString().trim();


                String getPassword = passwordLogin.getText().toString().trim();


                if (getEmail.isEmpty() || getPassword.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please Input Field", Toast.LENGTH_SHORT).show();
                } else {
                    if (isEmailValid(getEmail)) {
                        showToast("Invalid Email");
                    } else {
                        loginuser(getEmail, getPassword);
                    }

                }


            }
        });

    }

    public void GoSignup(View view) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        //LoginScreen.this.finish();
    }


    public void GoForgetPassword(View view) {

        startActivity(new Intent(getApplicationContext(), forgotpassword.class));
        LoginScreen.this.finish();
    }


    public void loginuser(String getEmail, String getPassword) {
        loginProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Login> call = retrofit.create(ApiInterface.class).LOGIN_CALL(getEmail, getPassword);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {

                    Login loginresp = response.body();
//                    if(loginresp.getStatus().equals("200")){}
                    if (loginresp.getMessage().equals("user is logged in")) {
                        Login login = new Login();
                        login = response.body();
                        Realm.init(LoginScreen.this);
                        RealmConfiguration config = new RealmConfiguration.Builder()
                                .name("poraquird.realm")
                                .schemaVersion(1)
                                .deleteRealmIfMigrationNeeded()
                                .build();
                        Realm.setDefaultConfiguration(config);

                        // add response to realm database

                        Realm realm = Realm.getInstance(config);
                        realm.beginTransaction();
                        realm.delete(UserInfo.class);
//                        realm.deleteAll();
                        realm.copyToRealm(login);
                        realm.commitTransaction();

                        int notesCount = realm.where(UserInfo.class).findAll().size();
                        Log.d("my first", String.valueOf(notesCount));
                        realm.close();

                        //login start main activity
                        new SharedPreferenceConfig().saveBooleanInSP("isLogin", true, LoginScreen.this);
                        new SharedPreferenceConfig().saveEmailOfUSerInSP("Email", getEmail, LoginScreen.this);
                        new SharedPreferenceConfig().savePawordOfUserInSP("Password", getPassword, LoginScreen.this);
                        String temp_name = loginresp.getUserInfo().getName();

                        String id = String.valueOf(loginresp.getUserInfo().getId());

                        new SharedPreferenceConfig().saveNameOfUSerInSP("name", temp_name, LoginScreen.this);
                        new SharedPreferenceConfig().saveidOfUSerInSP("id", id, LoginScreen.this);

                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                        intent.putExtra("Email", getEmail);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        LoginScreen.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
//                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                loginProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                loginProgressDialog.dismiss();
            }
        });


    }

//    private void confirmDialog(Context context){
//
//        final AlertDialog alert = new AlertDialog.Builder(
//                new ContextThemeWrapper(context,android.R.style.Theme_Dialog))
//                .create();
//        alert.setTitle("Alert");
//        alert.setMessage("Do you want to exit ?");
//        alert.setIcon(R.drawable.warn);
//        alert.setCancelable(false);
//        alert.setCanceledOnTouchOutside(false);
//
//        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        alert.dismiss();
//
//                        finish();
//
//                    }
//                });
//
//        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        alert.dismiss();
//
//                    }
//                });
//
//        alert.show();
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        confirmDialog(getApplicationContext());
//    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

}