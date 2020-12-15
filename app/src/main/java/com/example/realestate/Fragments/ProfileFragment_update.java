package com.example.realestate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.BottomSheets.BottomSheet;
import com.example.realestate.Model.Login;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.UserInfo;
import com.example.realestate.R;
import com.example.realestate.Utills.GlobalState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment_update extends Fragment {
    EditText username, name_company, phone_no, address, city, sector, Id, rnc, cell_no;
    Button submit, uploadImage, uploadFile;
    EditText tv_uploadFile;
    TextView tv_userName, tv_email;
    ImageView back_btn, iv_uploadImage;

    public ProfileFragment_update() {
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
        View view = inflater.inflate(R.layout.fragment_profile_update, container, false);


        tv_userName = view.findViewById(R.id.tv_userName);
        tv_email = view.findViewById(R.id.tv_email);


        username = view.findViewById(R.id.username);
        phone_no = view.findViewById(R.id.phoneno);
        address = view.findViewById(R.id.address);
        city = view.findViewById(R.id.city);
        sector = view.findViewById(R.id.sector);
        Id = view.findViewById(R.id.id);
        rnc = view.findViewById(R.id.rnc);
        cell_no = view.findViewById(R.id.cell_no);
        name_company = view.findViewById(R.id.name_compantname);


        submit = view.findViewById(R.id.SubmitBtn);
        uploadImage = view.findViewById(R.id.uploadimage);
        uploadFile = view.findViewById(R.id.uploadfile);
        iv_uploadImage = view.findViewById(R.id.iv_uploadimage);
        tv_uploadFile = view.findViewById(R.id.et_uploadfile);

        tv_userName.setText(GlobalState.getInstance().getUserInfo().getName());
        tv_email.setText(GlobalState.getInstance().getUserInfo().getEmail());


        back_btn = view.findViewById(R.id.back_btn_updateprofile);
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
                int id = GlobalState.getInstance().getUserInfo().getId();
                String RNC = rnc.getText().toString();
                String cellno1 = cell_no.getText().toString();
                String ID = Id.getText().toString();
                String UserName = username.getText().toString();
                String PhoneNo = phone_no.getText().toString();
                String address2 = address.getText().toString();
                String City = city.getText().toString();
                String Sector = sector.getText().toString();
                String Name_Compantname = name_company.getText().toString();


                if (Name_Compantname.isEmpty() || ID.isEmpty() || UserName.isEmpty() || address2.isEmpty() || City.isEmpty() || Sector.isEmpty() || cellno1.isEmpty()) {

                    Toast.makeText(getContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();

                } else {

                    updateProfile(id, UserName, PhoneNo, address2, City, Sector, ID, RNC, Name_Compantname, cellno1);
                }

            }
        });


        return view;
    }

    private void updateProfile(int id, String userName, String phone_no, String address, String city, String sector, String iD, String rnc, String compny_name, String cellNo) {

//        otpProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Login> call = retrofit.create(ApiInterface.class).UPDATEPROFIL_CALL(id, userName, phone_no, city, sector, iD, rnc, compny_name, address, cellNo);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    Login login = response.body();
                    if (login.getMessage().equals("Profile changed successfully")) {
                        ///
                        Toast.makeText(getContext(), "Profile changed successfully", Toast.LENGTH_SHORT).show();
                        UserInfo userInfo = new UserInfo();
                        userInfo = GlobalState.getInstance().getUserInfo();
//                        userInfo.setYour_id(id);
                        userInfo.setName(userName);
                        userInfo.setNumber(phone_no);
                        userInfo.setCity(city);
                        userInfo.setSector(sector);
                        userInfo.setYour_id(iD);
                        userInfo.setRnc(rnc);
                        userInfo.setCompany_name(compny_name);
                        userInfo.setAddress(address);
                        userInfo.setCell_number(cellNo);
                        GlobalState.getInstance().setUserInfo(userInfo);
                        tv_userName.setText(userName);

                        Fragment fragment = new ProfileFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameprofile, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();


                    } else {

                        Toast.makeText(getContext(), "Profile not Matches", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
//                otpProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                otpProgressDialog.dismiss();
            }
        });
    }


}