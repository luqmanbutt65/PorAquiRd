package com.example.realestate.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.realestate.Activities.BaseActivity;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class forgotpassword extends BaseActivity {
    EditText email;
    Button continu;
    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        email = findViewById(R.id.et_emailedittext);
        continu = findViewById(R.id.continuebtn);
        back_btn=findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(forgotpassword.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });


        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();


                if (Email.isEmpty()) {
                    showToast("Enter Email First");

                } else {
                    if (!is2EmailValid(Email)) {

                        showToast("Invalid Email");

                    } else {

                        ResetPass(Email);

                    }

                }


            }
        });
    }

    public void ResetPass(String getEmail) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Call<ResetPasswordResponse> call = retrofit.create(ApiInterface.class).RESET_PASWORD_CALL(getEmail);

        call.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {


                if (response.isSuccessful()) {

                    ResetPasswordResponse loginresp = response.body();
//                    if(loginresp.getStatus()==(200)){}
                    if (loginresp.getMessage().equals("code sent to your mail address")) {
                        //login start main activity
                        Toast.makeText(forgotpassword.this, "New Password sended to Gmail", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(forgotpassword.this, OTPScreenResetPass.class);
                        intent.putExtra("Email", getEmail);
                        startActivity(intent);


                    } else {
                        Toast.makeText(forgotpassword.this, "Email Not registered", Toast.LENGTH_SHORT).show();
                    }
//                    }

                } else {
                    Toast.makeText(forgotpassword.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Toast.makeText(forgotpassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}