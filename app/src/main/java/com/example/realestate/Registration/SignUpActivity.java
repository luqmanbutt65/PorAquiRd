package com.example.realestate.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Register;
import com.example.realestate.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    EditText userName, name, email, password;
    Button relativeLayout;
//    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
//            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName = findViewById(R.id.username);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        relativeLayout = findViewById(R.id.letTheUserSignUp);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String getUserName = userName.getText().toString().trim();
//                String getName = name.getText().toString().trim();
//                String getEmail = email.getText().toString().trim();
//                String getPassword = password.getText().toString().trim();
//
//                if (getUserName.isEmpty()) {
//                    userName.setError("Please Enter UserName");
//                } else if (getName.isEmpty()) {
//                    name.setError("Please Enter Name");
//                } else if (getEmail.isEmpty()) {
//                    email.setError("Please Enter Email");
//                } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText().toString()).find()) {
//                    email.setError("Invalid Email!");
//                } else if (getPassword.isEmpty()) {
//                    password.setError("Please Enter Password");
//                } else {
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                }
                signupnuser();

            }
        });
    }

    public void GoLogin(View view) {
        startActivity(new Intent(getApplicationContext(), LoginScreen.class));
    }

    public void signupnuser() {


        String getname = name.getText().toString().trim();
        String getemail= email.getText().toString().trim();
        String getPassword = password.getText().toString().trim();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Call<Register> call = retrofit.create(ApiInterface.class).REGISTER_CALL(getname, getemail,getPassword);

        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {


                if (response.isSuccessful()) {

                    Register loginresp = response.body();
//                    if(loginresp.getStatus()==(200)){}
                    if (loginresp.getMessage().equals("user is logged in")) {
                        //login start main activity
                        Intent intent = new Intent(SignUpActivity.this, LoginScreen.class);
                        intent.putExtra("Email", getemail);
                        startActivity(intent);

                    } else {
                        Toast.makeText(SignUpActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                    }
//                    }

                } else {
                    Toast.makeText(SignUpActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}




