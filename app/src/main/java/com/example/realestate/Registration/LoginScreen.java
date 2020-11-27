package com.example.realestate.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Login;
import com.example.realestate.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginScreen extends AppCompatActivity {
    EditText emailLogin, passwordLogin;
    Button relativeLayout;

    ProgressBar progressbar;
    //    String userEmail="Abc@gmail.com";
//    String PasswordUser="abcd";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        relativeLayout = findViewById(R.id.letTheUserLogIn);
//        progressbar = findViewById(R.id.progress);
        String getEmail = emailLogin.getText().toString().trim();
        String getPassword = passwordLogin.getText().toString().trim();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();

//                progressbar.setVisibility(View.VISIBLE);
//                if (getEmail.isEmpty()) {
//                    emailLogin.setError("Please Enter Email");
//                    progressbar.setVisibility(View.GONE);
//                }
////                else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(emailLogin.getText().toString()).find()) {
////                    progressbar.setVisibility(View.GONE);
////                    emailLogin.setError("Invalid Email!");
////                }
//                else if (getPassword.isEmpty()) {
//                    passwordLogin.setError("Please Enter Password");
//                    progressbar.setVisibility(View.GONE);
//                }
////                else if(getEmail.equals(userEmail)){
////                        progressbar.setVisibility(View.VISIBLE);
////                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
////                        progressbar.setVisibility(View.INVISIBLE);
////                        finish();
////                }
//                else {
//                    progressbar.setVisibility(View.INVISIBLE);
//                    Toast.makeText(LoginScreen.this, "Invalide Email/Password", Toast.LENGTH_SHORT).show();
//                }

//                if (getEmail.isEmpty() || getPassword.isEmpty()) {
//                    emailLogin.setError("Please Enter Data");
//                }
//                else if (getEmail.equals(userEmail)){
//                    if (getEmail.equals(PasswordUser)){
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//
//                    }
//                }
//                else {
//                    Toast.makeText(LoginScreen.this, "invalidate Email/Password", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }

    public void GoSignup(View view) {

        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void GoMain(View view) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void GoForgetPassword(View view) {

        startActivity(new Intent(getApplicationContext(), forgotpassword.class));
    }


    public void loginuser() {


        String getEmail = emailLogin.getText().toString().trim();
        String getPassword = passwordLogin.getText().toString().trim();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Call<Login> call = retrofit.create(ApiInterface.class).LOGIN_CALL(getEmail, getPassword);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {


                if (response.isSuccessful()) {

                    Login loginresp = response.body();
//                    if(loginresp.getStatus()==(200)){}
                        if (loginresp.getMessage().equals("user is logged in")) {
                            //login start main activity
                            Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                            intent.putExtra("Email", getEmail);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginScreen.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                        }
//                    }

                } else {
                    Toast.makeText(LoginScreen.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}