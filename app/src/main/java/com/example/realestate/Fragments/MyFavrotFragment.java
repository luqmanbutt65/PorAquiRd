package com.example.realestate.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.Adapters.MyFavAdapter;
import com.example.realestate.Adapters.MyprojectAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Like.PropertiesLike_Data;
import com.example.realestate.Model.Like.PropertiesLike_Response;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyFavrotFragment extends Fragment {
ImageView back_btn;
    Context context;
    TextView tv_result_number;
    RecyclerView favRecyclerview;
    private ArrayList<Properties> propertiesArrayList;
    public MyFavrotFragment() {
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
        View view = inflater.inflate(R.layout.fragment_my_favrot, container, false);
        String user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());
        getData(user_Id);
        propertiesArrayList = new ArrayList<>();
        context = this.getContext();
        tv_result_number=view.findViewById(R.id.tv_result_number);
        favRecyclerview= view.findViewById(R.id.myfav_recycler);
        favRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));


        back_btn=view.findViewById(R.id.back_btn1);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
    public void getData(String id) {
//        homeProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<PropertiesLike_Response> call = retrofit.create(ApiInterface.class).FAV_CALL(id);
        call.enqueue(new Callback<PropertiesLike_Response>() {
            @Override
            public void onResponse(Call<PropertiesLike_Response> call, Response<PropertiesLike_Response> response) {
                if (response.isSuccessful()) {
                    PropertiesLike_Response propertiesLike_response = response.body();
                    if (propertiesLike_response.getMessage().equals("user fav properties")) {

                       PropertiesLike_Data propertiesLike_data = response.body().getData();


                        if (propertiesLike_data != null) {
                            if (propertiesLike_data.getPropertiesArrayList() != null) {
                                propertiesArrayList = propertiesLike_data.getPropertiesArrayList();
                                GlobalState.getInstance().setPropertiesArrayList(propertiesArrayList);


                                if (propertiesArrayList.size() > 0) {
                                    tv_result_number.setText(String.valueOf(propertiesArrayList.size()));
                                    favRecyclerview.setAdapter(new MyFavAdapter(getActivity(), context, propertiesArrayList));


                                }

                            }

                        } else {
                            Toast.makeText(getContext(), "Data is null", Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        Toast.makeText(getContext(), "Data fetching error", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
//                homeProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PropertiesLike_Response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                homeProgressDialog.dismiss();
            }
        });

    }
}