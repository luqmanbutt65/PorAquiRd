package com.example.realestate.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class resetpassword extends AppCompatActivity {
EditText password,confirmpassword;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);


        password=findViewById(R.id.password1);
        confirmpassword=findViewById(R.id.confirmpassword1);
        submit=findViewById(R.id.submitbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Password=password.getText().toString();
                String ConfirmPassword=confirmpassword.getText().toString();
                if (Password.isEmpty() && ConfirmPassword.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Please fill the all fields",Toast.LENGTH_SHORT).show();
                }else if (Password!= ConfirmPassword){

                    Toast.makeText(getApplicationContext(),"password did not match",Toast.LENGTH_SHORT).show();

                }
                resetpass(Password);
            }
        });

    }



    public void resetpass(String password) {
//        otpProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<ResetPasswordResponse> call = retrofit.create(ApiInterface.class).RESETPASS_CALL(password);
        call.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                if (response.isSuccessful()) {
                    ResetPasswordResponse resetPasswordResponse = response.body();
                    if (resetPasswordResponse.getMessage().equals("password changed successfully")) {
                        //login start main activity
                        Intent intent = new Intent(resetpassword.this, LoginScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        resetpassword.this.finish();
                        Toast.makeText(resetpassword.this, "password changed successfully", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(resetpassword.this, "password not changed", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(resetpassword.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
//                otpProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Toast.makeText(resetpassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                otpProgressDialog.dismiss();
            }
        });

    }
}