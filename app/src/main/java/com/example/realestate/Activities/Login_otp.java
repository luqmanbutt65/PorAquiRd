package com.example.realestate.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.CustomeClasses.GenericTextWatcherNumber;
import com.example.realestate.Model.Login;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_otp extends BaseActivity {
    Button submit;
    EditText otp1, otp2, otp3, otp4, otp5, otp6;
    ProgressDialog ProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);


        ProgressDialog = new ProgressDialog(Login_otp.this);
        ProgressDialog.setMessage("Logining..."); // Setting Message
        ProgressDialog.setCancelable(false);

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


        submit = findViewById(R.id.submitloginotp);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ot1 = otp1.getText().toString();
                String ot2 = otp2.getText().toString();
                String ot3 = otp3.getText().toString();
                String ot4 = otp4.getText().toString();
                String ot5 = otp5.getText().toString();
                String ot6 = otp6.getText().toString();
                String otpmain = ot1 + ot2 + ot3 + ot4 + ot5 + ot6;

                if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", Login_otp.this)) {
                    if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", Login_otp.this)
                            != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", Login_otp.this) != null) {
                        String email1 = new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", Login_otp.this);
                        String passwrd = new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", Login_otp.this);
                        String number1 = new SharedPreferenceConfig().getenumberOfUSerFromSP("number", Login_otp.this);
                        if (ot1.isEmpty() || ot2.isEmpty() || ot3.isEmpty() || ot4.isEmpty() || ot5.isEmpty() || ot6.isEmpty()) {

                            showToast("please fill all fields");

                        } else {
                            OtpLogincall(otpmain, number1, email1, passwrd);
                        }
                    }

                } else {
                    showToast("you did not registerd");
                }
            }
        });


    }


    public void OtpLogincall(String otp, String number, String email, String password) {

        ProgressDialog.show();
        Call<Login> call = ApiClient.getRetrofit().create(ApiInterface.class).OTP_LOGIN_CALL(otp, number, email, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    Login login = response.body();
                    if (login.getMessage().equals("user is logged in")) {

                        Intent intent = new Intent(Login_otp.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Login_otp.this.finish();
                        showToast("User number Verified");

                    } else {

                        showToast("Incorrect OTP!");
                    }

                } else {


                    showToast("Error! Please try again!");
                }
                ProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

                showToast(t.getMessage());
                ProgressDialog.dismiss();
            }
        });
    }

}