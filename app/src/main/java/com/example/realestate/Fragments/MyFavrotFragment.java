package com.example.realestate.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.realestate.Model.Like.LikeResponse;
import com.example.realestate.Model.Like.PropertiesLike_Data;
import com.example.realestate.Model.Like.PropertiesLike_Response;
import com.example.realestate.Model.Login;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.UserInfo;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Sqldata.DataBaseHelper;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyFavrotFragment extends Fragment implements MyFavAdapter.ClickEventHandler {
    ImageView back_btn;
    Context context;
    TextView tv_result_number;
    RecyclerView favRecyclerview;
    String user_Id = "";
    private ArrayList<Properties> propertiesArrayList;
    DataBaseHelper dataBaseHelper;

    public MyFavrotFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", context)) {
            getData(user_Id);
        }






    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_favrot, container, false);
        context = this.getContext();
        dataBaseHelper = new DataBaseHelper(getContext());
        user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());

        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", context)) {
            getData(user_Id);
        }

        propertiesArrayList = new ArrayList<>();

        tv_result_number = view.findViewById(R.id.tv_result_number);
        favRecyclerview = view.findViewById(R.id.myfav_recycler);
        favRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));


        back_btn = view.findViewById(R.id.back_btn1);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        if (!new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext())) {
            dataBaseHelper = new DataBaseHelper(getContext());
            propertiesArrayList = dataBaseHelper.ViewData();
            if (propertiesArrayList != null) {
                if (propertiesArrayList.size() > 0) {
                    setRecyclerView(propertiesArrayList);
                }

            }}
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

                                    setRecyclerView(propertiesArrayList);

//                                    favRecyclerview.setAdapter(new MyFavAdapter(getActivity(), context, propertiesArrayList,this));

//                                    PropertiesLike_Response propertiesLike_response3 = new PropertiesLike_Response();
//                                    propertiesLike_response3= response.body();
//                                    Realm.init(getContext());
//                                    RealmConfiguration config = new RealmConfiguration.Builder()
//                                            .name("poraquird.realm")
//                                            .schemaVersion(1)
//                                            .deleteRealmIfMigrationNeeded()
//                                            .build();
//                                    Realm.setDefaultConfiguration(config);
//
//                                    // add response to realm database
//
//                                    Realm realm = Realm.getInstance(config);
//                                    realm.beginTransaction();
////                                    realm.delete(UserInfo.class);
////                        realm.deleteAll();
//                                    realm.copyToRealm(propertiesLike_response3);
//                                    realm.commitTransaction();
//
//                                    int notesCount = realm.where(Properties.class).findAll().size();
//                                    Log.d("my second", String.valueOf(notesCount));
//                                    realm.close();

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

    public void setRecyclerView(ArrayList<Properties> propertiesArrayListNew) {
        favRecyclerview.setAdapter(new MyFavAdapter(getActivity(), context, propertiesArrayListNew, this));

    }

    @Override
    public void handleClick(int count) {
//        getpropertydata(String user_id, int property_id)
        tv_result_number.setText(String.valueOf(count));
    }

//    public void getpropertydata(String user_id, int property_id) {
////        descriptionProgressDialog.show();
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        Call<LikeResponse> call = retrofit.create(ApiInterface.class).LIKEPROPERTY_CALL(user_id, String.valueOf(property_id));
//        call.enqueue(new Callback<LikeResponse>() {
//            @Override
//            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
//                if (response.isSuccessful()) {
//                    LikeResponse likeResponse = response.body();
//                    if (likeResponse.getMessage().equals("Liked")) {
//
//
//                    } else if (likeResponse.getMessage().equals("Unliked")) {
//
//
//                    } else {
//                        Toast.makeText(context, "Error Please try again", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
////                descriptionProgressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<LikeResponse> call, Throwable t) {
//                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
////                descriptionProgressDialog.dismiss();
//            }
//        });
//
//    }
}