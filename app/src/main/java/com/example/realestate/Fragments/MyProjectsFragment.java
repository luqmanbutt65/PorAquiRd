package com.example.realestate.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.MyFavAdapter;
import com.example.realestate.Adapters.MyprojectAdapter;
import com.example.realestate.Activities.Adddata;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.MyProject.MyProperties_Data;
import com.example.realestate.Model.MyProject.MyProperties_Response;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyProjectsFragment extends Fragment implements MyFavAdapter.ClickEventHandler {

    Context context;
    ImageView imageView, backbtn;
    TextView tv_result_number;
    RecyclerView myproRecyclerview;
    ProgressDialog myprogressdilouge;
    private ArrayList<Properties> propertiesArrayList;

    public MyProjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_projects, container, false);
        myprogressdilouge = new ProgressDialog(getContext());
        myprogressdilouge.setMessage("Loading ...");
        myprogressdilouge.setCancelable(false);
        String user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());
        getData(user_Id);
        propertiesArrayList = new ArrayList<>();
        context = this.getContext();

        tv_result_number = view.findViewById(R.id.myprojectrecycler);
        myproRecyclerview = view.findViewById(R.id.myproject_recycler);
        myproRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));


        imageView = view.findViewById(R.id.addProject);

        backbtn = view.findViewById(R.id.back_btn_myproject);

        backbtn.setOnClickListener(new View.OnClickListener() {
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


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalState.getInstance().getUserInfo() != null) {
                    if (GlobalState.getInstance().getUserInfo().getUpload_limit() != null) {

                        if (GlobalState.getInstance().getUserInfo().getUpload_limit().equals("0")) {

                            Toast.makeText(context, "Your Property Upload limit Expired", Toast.LENGTH_SHORT).show();

                        } else {
                            startActivity(new Intent(getActivity(), Adddata.class));

                        }


                    } else {

                        Toast.makeText(context, "Your Property Upload limit Expired", Toast.LENGTH_SHORT).show();


                    }

                } else {
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                    Toast.makeText(context, "Connect to internet Please", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;
    }

    public void getData(String id) {
        myprogressdilouge.show();

        Call<MyProperties_Response> call = ApiClient.getRetrofit().create(ApiInterface.class).MYPROJECT_CALL(id);

        call.enqueue(new Callback<MyProperties_Response>() {
            @Override
            public void onResponse(Call<MyProperties_Response> call, Response<MyProperties_Response> response) {
                if (response.isSuccessful()) {
                    MyProperties_Response myProperties_response = response.body();
                    if (myProperties_response.getMessage().equals("user uploaded properties")) {

                        MyProperties_Data myProperties_data = response.body().getData();


                        if (myProperties_data != null) {
                            if (myProperties_data.getPropertiesArrayList() != null) {
                                propertiesArrayList = myProperties_data.getPropertiesArrayList();
                                GlobalState.getInstance().setPropertiesArrayList(propertiesArrayList);


                                if (propertiesArrayList.size() > 0) {
                                    String s = String.valueOf(propertiesArrayList.size());
                                    tv_result_number.setText(s + " Projects");
                                    setRecyclerView(propertiesArrayList);

                                } else {
                                    tv_result_number.setText("N/A");

                                }

                            }

                        } else {
                            Toast.makeText(getContext(), "Data is null", Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        Toast.makeText(getContext(), " Null Data", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                myprogressdilouge.dismiss();
            }

            @Override
            public void onFailure(Call<MyProperties_Response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                myprogressdilouge.dismiss();
            }
        });

    }

    public void setRecyclerView(ArrayList<Properties> propertiesArrayListNew) {
        myproRecyclerview.setAdapter(new MyprojectAdapter(getActivity(), context, propertiesArrayListNew, this::handleClick));

    }

    @Override
    public void handleClick(int count) {
        tv_result_number.setText(String.valueOf(count));
    }
}