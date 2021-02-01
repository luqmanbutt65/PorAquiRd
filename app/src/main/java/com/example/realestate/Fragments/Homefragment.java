package com.example.realestate.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.BottomSheets.BottomSheet;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.SetMapdataInterface;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Sqldata.DataBaseHelper;
import com.example.realestate.Utills.GlobalState;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.LOCATION_SERVICE;


public class Homefragment extends Fragment {
    ImageView drawerbtn, drawerbtnCancle, filter, notification;
    Button menu, termConditions, privecyPolicy, logout, cookies_policy, cockies_policy, Connectors, plans, aboutus, contactus, login;
    Context context;
    EditText search;
    DashBoardAdapter dashBoardAdapter;
    RecyclerView homeRecylerView;
    TextView tv_result_number;
    ProgressDialog homeProgressDialog;
    TextView tv_username;
    Properties properties;
    ArrayList<Properties> offLoginFavPropertites = new ArrayList<>();
    DataBaseHelper dataBaseHelper;
    //ads
    InterstitialAd mInterstitialAd;
    private FrameLayout frameLayout;
    private ArrayList<Properties> propertiesArrayList;
    private ArrayList<Properties> filteredpropertiesArrayList;
    private ArrayList<Properties> nonFiltredpropertyArrayList;
    private DrawerLayout mDrawerLayout;

    public Homefragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        statusCheck2();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);
        context = this.getContext();

        dataBaseHelper = new DataBaseHelper(context);
        offLoginFavPropertites = dataBaseHelper.ViewData();

        //add
//        MobileAds.initialize(getContext(), getString(R.string.admob_app_id));
//        mInterstitialAd = new InterstitialAd(getContext());
//        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        mInterstitialAd.loadAd(adRequest);

        mDrawerLayout = view.findViewById(R.id.frame1);
        //   mDrawerLayout.closeDrawer(Gravity.LEFT, true);

        tv_result_number = view.findViewById(R.id.tv_result);
        propertiesArrayList = new ArrayList<>();
        homeProgressDialog = new ProgressDialog(getContext());
        homeProgressDialog.setMessage("Loading..."); // Setting Message
        homeProgressDialog.setCancelable(false);
        tv_username = view.findViewById(R.id.tv_username);
        login = view.findViewById(R.id.login1);

        homeRecylerView = view.findViewById(R.id.homeRecylerView);
        homeRecylerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        notification = view.findViewById(R.id.notification);
        cockies_policy = view.findViewById(R.id.cockies_policy);
        Connectors = view.findViewById(R.id.Connectors);
        filter = view.findViewById(R.id.filter);
        search = view.findViewById(R.id.search);
        drawerbtn = view.findViewById(R.id.drawer);
        cookies_policy = view.findViewById(R.id.cockies_policy);


        aboutus = view.findViewById(R.id.aboutus);
        contactus = view.findViewById(R.id.ccontactus);

        menu = view.findViewById(R.id.menu);
        plans = view.findViewById(R.id.plans);
        privecyPolicy = view.findViewById(R.id.privacypolicy);
        termConditions = view.findViewById(R.id.termcondition);
        logout = view.findViewById(R.id.logout);


        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext())) {
            login.setVisibility(View.INVISIBLE);
            logout.setVisibility(View.VISIBLE);
        } else {
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.INVISIBLE);
        }


        cockies_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PrivecyPolicy();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        drawerbtnCancle = view.findViewById(R.id.cancel_button);

//        mInterstitialAd.setAdListener(new AdListener() {
//            public void onAdLoaded() {
//                showInterstitial();
//            }
//        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new AboutUs();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ContactUs();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                ArrayList<Properties> properties1 = new ArrayList<>();
                if (s.length() > 3) {
                    for (Properties x : propertiesArrayList) {
                        if (x.getLocation().toLowerCase().contains(s) || x.getTitle().toLowerCase().contains(s) || x.getCity().toLowerCase().contains(s) || x.getProperty_type().toLowerCase().contains(s)) {
                            properties1.add(x);
                            Log.e("size", "resulttxtchnge" + x.getTitle());

                        }


                    }
                    homeRecylerView.setAdapter(new DashBoardAdapter(getActivity(), context, properties1));
                    tv_result_number.setText(Integer.toString(properties1.size()));

                }

                if (s.length() == 0) {
                    homeRecylerView.setAdapter(new DashBoardAdapter(getActivity(), context, propertiesArrayList));
                    tv_result_number.setText(Integer.toString(propertiesArrayList.size()));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        plans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PlanSub();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        Connectors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new Connector();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (new SharedPreferenceConfig().getBooleanLanguagefrenchFromSP("frenchlanguage", getContext())) {


                    new SharedPreferenceConfig().saveBooleanLanguagefrenchInSP("frenchlanguage", true, getContext());

                } else if (new SharedPreferenceConfig().getBooleanLanguageFromSP("language", getContext())) {
                    new SharedPreferenceConfig().clearSharedPrefrence(getContext());
                    new SharedPreferenceConfig().saveBooleanLanguageInSP("language", true, getContext());
                } else if (new SharedPreferenceConfig().getBooleanLanguagespanishFromSP("spanishlanguage", getContext())) {
                    new SharedPreferenceConfig().clearSharedPrefrence(getContext());
                    new SharedPreferenceConfig().saveBooleanLanguagespanishInSP("spanishlanguage", true, getContext());
                } else {
                    new SharedPreferenceConfig().clearSharedPrefrence(getContext());
                }

//                new SharedPreferenceConfig().clearSharedPrefrence(getContext());
                Intent intent = new Intent(getActivity(), LoginScreen.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (new SharedPreferenceConfig().getBooleanLanguagefrenchFromSP("frenchlanguage", getContext())) {

                    new SharedPreferenceConfig().clearSharedPrefrence(getContext());
                    new SharedPreferenceConfig().saveBooleanLanguagefrenchInSP("frenchlanguage", true, getContext());

                } else if (new SharedPreferenceConfig().getBooleanLanguageFromSP("language", getContext())) {
                    new SharedPreferenceConfig().clearSharedPrefrence(getContext());
                    new SharedPreferenceConfig().saveBooleanLanguageInSP("language", true, getContext());
                } else if (new SharedPreferenceConfig().getBooleanLanguagespanishFromSP("spanishlanguage", getContext())) {
                    new SharedPreferenceConfig().clearSharedPrefrence(getContext());
                    new SharedPreferenceConfig().saveBooleanLanguagespanishInSP("spanishlanguage", true, getContext());
                }

//                new SharedPreferenceConfig().clearSharedPrefrence(getContext());
                Intent intent = new Intent(getActivity(), LoginScreen.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        privecyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PrivecyPolicy();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        termConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new TermConditions();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        drawerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawers();
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        drawerbtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLayout.closeDrawer(GravityCompat.START, false);
            }
        });

        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext())) {
            if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext())
                    != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", getContext()) != null) {
                String name = new SharedPreferenceConfig().getNameOfUSerFromSP("name", getContext());
                tv_username.setText("Welcome," + name);
            }
        }
//        mDrawerLayout = view.findViewById(R.id.drawerlayout);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!statusCheck()) {

                    Toast.makeText(context, "GPS Not On", Toast.LENGTH_SHORT).show();

                } else {

//                    homeProgressDialog.show();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        public void run() {

                    Fragment fragment = new BottomSheet();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame1, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    homeProgressDialog.dismiss();
                }
//                    }, 4000);
//                }


            }
        });

//        drawerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout = view.findViewById(R.id.drawerlayout);
//                drawerLayout.openDrawer(Gravity.LEFT, true);
//            }
//        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext())) {
                    Fragment fragment = new Notifications();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContainer, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(context, "You are not login", Toast.LENGTH_SHORT).show();
                }


            }
        });


        //TODO: CAll the Api for Get the List of All Avaiable Properties Houses
        String user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", context);
        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext())) {
            if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext())
                    != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", getContext()) != null) {


                if (GlobalState.getInstance().isFilteredOk()) {

                    GlobalState.getInstance().setFilteredOk(false);
                    filteredpropertiesArrayList = GlobalState.getInstance().getFilteredPropertiesArrayList();
                    if (filteredpropertiesArrayList != null) {
                        if (filteredpropertiesArrayList.size() > 0) {
                            tv_result_number.setText(String.valueOf(filteredpropertiesArrayList.size()));
                            propertiesArrayList.clear();
                            propertiesArrayList = filteredpropertiesArrayList;
                            homeRecylerView.setAdapter(new DashBoardAdapter(getActivity(), context, propertiesArrayList));

                        }
                    }

                } else {
                    getlikeData(user_Id);

                }


            }
        } else {

            if (GlobalState.getInstance().isFilteredOk()) {
                GlobalState.getInstance().setFilteredOk(false);
                filteredpropertiesArrayList = GlobalState.getInstance().getFilteredPropertiesArrayList();
                if (filteredpropertiesArrayList != null) {
                    if (filteredpropertiesArrayList.size() > 0) {
                        tv_result_number.setText(String.valueOf(filteredpropertiesArrayList.size()));
                        propertiesArrayList.clear();
                        propertiesArrayList = filteredpropertiesArrayList;
                        homeRecylerView.setAdapter(new DashBoardAdapter(getActivity(), context, propertiesArrayList));

                    }
                }

            } else {

                getlikeData("0");


            }

        }

        return view;
    }


//    public void getData() {
//        homeProgressDialog.show();
//        int id = 0;
//        Call<Properties_Response> call = ApiClient.getRetrofit().create(ApiInterface.class).DASHBOARDDATA_CALL(id);
//        call.enqueue(new Callback<Properties_Response>() {
//            @Override
//            public void onResponse(Call<Properties_Response> call, Response<Properties_Response> response) {
//                if (response.isSuccessful()) {
//                    Properties_Response properties_response = response.body();
//                    if (properties_response.getMessage().equals("all properties")) {
//
//                        Properties_Data properties_data = response.body().getData();
//
//
//                        if (properties_data != null) {
//                            if (properties_data.getPropertiesArrayList() != null) {
//                                propertiesArrayList = properties_data.getPropertiesArrayList();
//                                GlobalState.getInstance().setPropertiesArrayList(propertiesArrayList);
//
//
//                                ArrayList<Properties> tempTestList = GlobalState.getInstance().getPropertiesArrayList();
//
//                                if (propertiesArrayList.size() > 0) {
//                                    tv_result_number.setText(String.valueOf(propertiesArrayList.size()));
//                                    homeRecylerView.setAdapter(new DashBoardAdapter(getActivity(), context, propertiesArrayList));
//
//                                }
//
//                            }
//
//                        } else {
//                            Toast.makeText(getContext(), "Data is null", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    } else {
//
//                        Toast.makeText(getContext(), "Data fetching error", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                } else {
//
//                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
//                }
//                homeProgressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<Properties_Response> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                homeProgressDialog.dismiss();
//            }
//        });
//
//    }

    private void calladapter() {
        homeRecylerView.setAdapter(new DashBoardAdapter(getActivity(), context, propertiesArrayList));

    }

    public void getlikeData(String id) {
        homeProgressDialog.show();
        Call<Properties_Response> call = ApiClient.getRetrofit().create(ApiInterface.class).LIKEPROPERTY_CALL(id);
        call.enqueue(new Callback<Properties_Response>() {
            @Override
            public void onResponse(Call<Properties_Response> call, Response<Properties_Response> response) {
                if (response.isSuccessful()) {
                    Properties_Response properties_response = response.body();
                    if (properties_response.getMessage().equals("all properties")) {

                        Properties_Data properties_data = response.body().getData();


                        if (properties_data != null) {
                            if (properties_data.getPropertiesArrayList() != null) {
                                nonFiltredpropertyArrayList = properties_data.getPropertiesArrayList();
                                GlobalState.getInstance().setPropertiesArrayList(nonFiltredpropertyArrayList);


                                if (nonFiltredpropertyArrayList.size() > 0) {
                                    propertiesArrayList.clear();
                                    propertiesArrayList = nonFiltredpropertyArrayList;
                                    tv_result_number.setText(String.valueOf(nonFiltredpropertyArrayList.size()));
                                    for (int i = 0; i < propertiesArrayList.size(); i++) {
                                        for (int j = 0; j < offLoginFavPropertites.size(); j++) {
                                            if (propertiesArrayList.get(i).getId() == offLoginFavPropertites.get(j).getId()) {
                                                propertiesArrayList.get(i).setLike(true);
                                            }
                                        }
                                    }
                                    homeRecylerView.setAdapter(new DashBoardAdapter(getActivity(), context, propertiesArrayList));

                                }

                            }

                        } else {
                            Toast.makeText(getContext(), "Data is null", Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        Toast.makeText(getContext(), "Data null", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    //   Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                homeProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Properties_Response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                homeProgressDialog.dismiss();
            }
        });

    }

    public boolean statusCheck() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            statusCheck2();
            return false;

        } else {
            return true;
        }
    }

    public void statusCheck2() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                getCurrentLocation();
            }
            //st.toast("Enabled");

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void getCurrentLocation() {

        final LocationRequest locationRequest = new LocationRequest();

        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


//        LocationServices.getFusedLocationProviderClient(getActivity())
//                .requestLocationUpdates(locationRequest, new LocationCallback() {
//
//                    @Override
//                    public void onLocationResult(LocationResult locationResult) {
//                        super.onLocationResult(locationResult);
//
//                        homeProgressDialog.show();
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            public void run() {
//                                homeProgressDialog.dismiss();
//                            }
//                        }, 3000); // 3000 milliseconds delay
//
//
//                        LocationServices.getFusedLocationProviderClient(context).removeLocationUpdates(this);
//                        if (locationResult != null && locationResult.getLocations().size() > 0) {
//                            int latestlocationIndex = locationResult.getLocations().size() - 1;
//                            double latitude = locationResult.getLocations().get(latestlocationIndex).getLatitude();
//                            double longitude = locationResult.getLocations().get(latestlocationIndex).getLongitude();
//
////                            sp.saveStringValue("latitude", String.valueOf(latitude));
////                            sp.saveStringValue("longitude", String.valueOf(longitude));
//                        }
//
//                    }
//                }, Looper.myLooper());

    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}