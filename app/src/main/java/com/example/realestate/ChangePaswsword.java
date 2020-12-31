package com.example.realestate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Fragments.ProfileFragment;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.Registration.resetpassword;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChangePaswsword extends Fragment {
    ImageView back_btn;
    Button submit;
    EditText oldpass, newpass, confirmpass;
    TextView name, email;

    public ChangePaswsword() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_paswsword, container, false);

        oldpass = view.findViewById(R.id.oldPass);
        newpass = view.findViewById(R.id.newPass);
        confirmpass = view.findViewById(R.id.confirmPass);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);

        submit = view.findViewById(R.id.submit);

        name.setText(new SharedPreferenceConfig().getNameOfUSerFromSP("name", getContext()));
        email.setText(new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext()));
//        name.setText(GlobalState.getInstance().getUserInfo().getName());
//        email.setText(GlobalState.getInstance().getUserInfo().getEmail());
        String Oldpass = oldpass.getText().toString();
        String Newpass = newpass.getText().toString();
        String Confirmpass = confirmpass.getText().toString();


        back_btn = view.findViewById(R.id.back_btn_changepass);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProfileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Oldpass = oldpass.getText().toString();
                String Newpass = newpass.getText().toString();
                String Confirmpass = confirmpass.getText().toString();


                if (Oldpass.isEmpty() && Newpass.isEmpty() && Confirmpass.isEmpty()) {

                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {

                    if (Confirmpass == Newpass) {
                        Toast.makeText(getContext(), "password did not Match", Toast.LENGTH_SHORT).show();
                    } else {
                        resetPass(GlobalState.getInstance().getUserInfo().getId(), Oldpass, Newpass, Confirmpass, GlobalState.getInstance().getUserInfo().getEmail());
                    }

                }


            }
        });

        return view;
    }


    public void resetPass(int id, String oldPass, String newPass, String confirmPass, String email) {
//        otpProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        //Call<ResetPasswordResponse> call = retrofit.create(ApiInterface.class).RESETPROFILEPASS_CALL(String.valueOf(id),oldPass,newPass,confirmPass,email);
        Call<ResetPasswordResponse> call = retrofit.create(ApiInterface.class).RESETPROFILEPASS_CALL(id, oldPass, newPass, confirmPass, email);

        call.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                if (response.isSuccessful()) {
                    ResetPasswordResponse resetPasswordResponse = response.body();
                    if (resetPasswordResponse.getMessage().equals("Password changed successfully")) {

                        Toast.makeText(getContext(), "password changed successfully", Toast.LENGTH_SHORT).show();
                        new SharedPreferenceConfig().savePawordOfUserInSP("Password", newPass, getContext());
                        // Intent intent=new Intent()

                        Intent intent = new Intent(getActivity(), LoginScreen.class);
                        startActivity(intent);
                    } else {

                        Toast.makeText(getContext(), "password not changed", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
//                otpProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                otpProgressDialog.dismiss();
            }
        });

    }


}