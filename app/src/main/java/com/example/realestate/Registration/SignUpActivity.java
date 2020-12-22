package com.example.realestate.Registration;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ColorStateListInflaterCompat;

import com.example.realestate.Activities.BaseActivity;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Register;
import com.example.realestate.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//3-12-2020
public class SignUpActivity extends BaseActivity {
    EditText userName, name, email, password;
    Button register_btn;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName = findViewById(R.id.username);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);

        register_btn = findViewById(R.id.letTheUserSignUp);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String getname = name.getText().toString().trim();
                String getemail = email.getText().toString().trim();
                String getPassword = password.getText().toString().trim();

                if (getname.isEmpty() || getemail.isEmpty() || getPassword.isEmpty()) {

                    Toast.makeText(SignUpActivity.this, "Please Input Field", Toast.LENGTH_SHORT).show();
                } else {
                    if (getPassword.length() < 8) {

                        Toast.makeText(SignUpActivity.this, "password is less than 8 characters", Toast.LENGTH_SHORT).show();
                    } else {
                        signupnuser(getname, getemail, getPassword);
                    }


//                    if (isEmailValid(getemail)) {
//
//
//                    }
//                    else {
//                        showToast("Invalid Email");
//                    }
                }


            }
        });
    }

    public void GoLogin(View view) {

        startActivity(new Intent(getApplicationContext(), LoginScreen.class));
        SignUpActivity.this.finish();

    }

    public void signupnuser(String getname, String getemail, String getPassword) {
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Register> call = retrofit.create(ApiInterface.class).REGISTER_CALL(getname, getemail, getPassword);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful()) {
                    Register loginresp = response.body();
                    if (loginresp.getMessage().equals("user registered")) {
                        //login start main activity
                        Toast.makeText(SignUpActivity.this, "User registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, OTPScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("Email", getemail);
                        startActivity(intent);
                        SignUpActivity.this.finish();
                    } else if (loginresp.getMessage().equals("Email already used, try some different Email.")) {
                        Toast.makeText(SignUpActivity.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Server Error! Please try again!", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(SignUpActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
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
////        alert.setIcon(R.drawable.warning_icon);
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
                        SignUpActivity.this.finish();
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




