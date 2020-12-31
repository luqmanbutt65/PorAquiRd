package com.example.realestate.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.CustomeClasses.GenericTextWatcherNumber;
import com.example.realestate.Model.Cell_OTP.Otp_response;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mobile_register_otp extends BaseActivity {
    Button submitotp;
    EditText otp1, otp2, otp3, otp4, otp5, otp6;
    ProgressDialog profileProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_register_otp);

        submitotp = findViewById(R.id.submitotp);

        profileProgressDialog = new ProgressDialog(Mobile_register_otp.this);
        profileProgressDialog.setMessage("Logining..."); // Setting Message
        profileProgressDialog.setCancelable(false);
        otp1 = findViewById(R.id.txtOTP_1);
        otp2 = findViewById(R.id.txtOTP_2);
        otp3 = findViewById(R.id.txtOTP_3);
        otp4 = findViewById(R.id.txtOTP_4);
        otp5 = findViewById(R.id.txtOTP_5);
        otp6 = findViewById(R.id.txtOTP_6);
        String ot1 = otp1.getText().toString();
        String ot2 = otp2.getText().toString();
        String ot3 = otp3.getText().toString();
        String ot4 = otp4.getText().toString();
        String ot5 = otp5.getText().toString();
        String ot6 = otp6.getText().toString();

        EditText[] OTP = {otp1, otp2, otp3, otp4, otp5, otp6};

        otp1.addTextChangedListener(new GenericTextWatcherNumber(otp1, OTP));
        otp2.addTextChangedListener(new GenericTextWatcherNumber(otp2, OTP));
        otp3.addTextChangedListener(new GenericTextWatcherNumber(otp3, OTP));
        otp4.addTextChangedListener(new GenericTextWatcherNumber(otp4, OTP));
        otp5.addTextChangedListener(new GenericTextWatcherNumber(otp5, OTP));
        otp6.addTextChangedListener(new GenericTextWatcherNumber(otp6, OTP));


        submitotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ot1 = otp1.getText().toString();
                String ot2 = otp2.getText().toString();
                String ot3 = otp3.getText().toString();
                String ot4 = otp4.getText().toString();
                String ot5 = otp5.getText().toString();
                String ot6 = otp6.getText().toString();
                String otpmain = ot1 + ot2 + ot3 + ot4 + ot5 + ot6;
                String user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", Mobile_register_otp.this);
                if (ot1.isEmpty() || ot2.isEmpty() || ot3.isEmpty() || ot4.isEmpty() || ot5.isEmpty() || ot6.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please fill all fields", Toast.LENGTH_SHORT).show();

                } else {
                }

                Otpcall(user_id, otpmain);

            }


        });

    }

    public void Otpcall(String id, String otpnumber) {
        profileProgressDialog.show();
        Call<Otp_response> call = ApiClient.getRetrofit().create(ApiInterface.class).OTP_CHECK_CALL(id, otpnumber);
        call.enqueue(new Callback<Otp_response>() {
            @Override
            public void onResponse(Call<Otp_response> call, Response<Otp_response> response) {
                if (response.isSuccessful()) {
                    Otp_response otp_response = response.body();
                    if (otp_response.getMessage().equals("Login By OTP is activated")) {
                        Intent intent = new Intent(Mobile_register_otp.this, MainActivity.class);
                        startActivity(intent);

                        showToast("User OTP Verified");

                    } else {


                        showToast("Incorrect OTP!");
                    }

                } else {


                    showToast("Error! Please try again!");
                }
                profileProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Otp_response> call, Throwable t) {

                showToast(t.getMessage());
                profileProgressDialog.dismiss();
            }
        });
    }
}