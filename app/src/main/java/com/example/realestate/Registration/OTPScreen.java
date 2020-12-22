package com.example.realestate.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.CustomeClasses.GenericTextWatcher;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OTPScreen extends AppCompatActivity {
    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four;
    Button verify_otp;
    ProgressDialog otpProgressDialog;
    TextView resend_otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_screen);

        resend_otp = findViewById(R.id.resend_otp);
//        resend_otp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String Otp_textbox_one = otp_textbox_one.getText().toString();
//                String Otp_textbox_two = otp_textbox_two.getText().toString();
//                String Otp_textbox_three = otp_textbox_three.getText().toString();
//                String Otp_textbox_four = otp_textbox_four.getText().toString();
//
//                String OTPCode = Otp_textbox_one + Otp_textbox_two + Otp_textbox_three + Otp_textbox_four;
//                if (Otp_textbox_one.isEmpty() && Otp_textbox_two.isEmpty() && Otp_textbox_three.isEmpty() && Otp_textbox_four.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
//
//                } else {
//                }
////                {Toast.makeText(getApplicationContext(), "Entered successfully", Toast.LENGTH_SHORT).show();}
//                getOtp(OTPCode);
//            }
//        });

        otp_textbox_one = findViewById(R.id.otp_edit_box1);
        otp_textbox_two = findViewById(R.id.otp_edit_box2);
        otp_textbox_three = findViewById(R.id.otp_edit_box3);
        otp_textbox_four = findViewById(R.id.otp_edit_box4);
        verify_otp = findViewById(R.id.verify_otp_btn);

        otpProgressDialog = new ProgressDialog(OTPScreen.this);
        otpProgressDialog.setMessage("Logining..."); // Setting Message
        otpProgressDialog.setCancelable(false);
        EditText[] OTP = {otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four};

        otp_textbox_one.addTextChangedListener(new GenericTextWatcher(otp_textbox_one, OTP));
        otp_textbox_two.addTextChangedListener(new GenericTextWatcher(otp_textbox_two, OTP));
        otp_textbox_three.addTextChangedListener(new GenericTextWatcher(otp_textbox_three, OTP));
        otp_textbox_four.addTextChangedListener(new GenericTextWatcher(otp_textbox_four, OTP));


        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Otp_textbox_one = otp_textbox_one.getText().toString();
                String Otp_textbox_two = otp_textbox_two.getText().toString();
                String Otp_textbox_three = otp_textbox_three.getText().toString();
                String Otp_textbox_four = otp_textbox_four.getText().toString();

                String OTPCode = Otp_textbox_one + Otp_textbox_two + Otp_textbox_three + Otp_textbox_four;
                if (Otp_textbox_one.isEmpty() || Otp_textbox_two.isEmpty() || Otp_textbox_three.isEmpty() || Otp_textbox_four.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please fill all fields", Toast.LENGTH_SHORT).show();

                } else {
                }
//                {Toast.makeText(getApplicationContext(), "Entered successfully", Toast.LENGTH_SHORT).show();}
                getOtp(OTPCode);

            }
        });

    }

    public void getOtp(String otpcode) {
        otpProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<ResetPasswordResponse> call = retrofit.create(ApiInterface.class).OTP_CALL(otpcode);
        call.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                if (response.isSuccessful()) {
                    ResetPasswordResponse resetPasswordResponse = response.body();
                    if (resetPasswordResponse.getMessage().equals("user verified")) {
                        //login start main activity
                        Intent intent = new Intent(OTPScreen.this, LoginScreen.class);
                        Toast.makeText(OTPScreen.this, "User verified", Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        OTPScreen.this.finish();
                    } else {

                        Toast.makeText(OTPScreen.this, "OTP is Incorrect", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(OTPScreen.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                otpProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Toast.makeText(OTPScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                otpProgressDialog.dismiss();
            }
        });

    }

    public void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @Override
    public void onBackPressed() {
        minimizeApp();
    }
}