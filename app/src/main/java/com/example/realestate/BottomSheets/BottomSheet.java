package com.example.realestate.BottomSheets;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.realestate.Activities.Adddata;
import com.example.realestate.Activities.BaseActivity;
import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Activities.UpdateData;
import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.CustomeClasses.NumberTextWatcher;
import com.example.realestate.Fragments.Homefragment;
import com.example.realestate.Fragments.MapsFragment;
import com.example.realestate.Fragments.PaymentWeb;
import com.example.realestate.Model.Filter.FilterResponse;
import com.example.realestate.Model.GetList.Cities_Data;
import com.example.realestate.Model.GetList.City;
import com.example.realestate.Model.GetList.GetCitiesListResponse;
import com.example.realestate.Model.GetList.GetListPropertyType.GetpropertyListResponse;
import com.example.realestate.Model.GetList.GetListPropertyType.PropertyType;
import com.example.realestate.Model.GetList.GetListPropertyType.PropertyType_Data;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.R;
import com.example.realestate.SetMapdataInterface;
import com.example.realestate.Utills.AutoCompleteAdapter;
import com.example.realestate.Utills.GlobalState;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.LOCATION_SERVICE;


public class BottomSheet extends Fragment {
    public static final String[] category = new String[]{"House", "Office", "Shop"};
    AutoCompleteTextView autoCompleteTextView;
    RelativeLayout forRent, forSale, maximumButton, minimumButton;
    AppCompatButton BedroomAny, BathroomAny, oneBedroom, oneBathroom, twoBedroom, twoBathroom, threeBedroom, threeBathroom,
            fourBedroom, fourBathroom, enterBedroom, enterBathroom, applyFilters;
    ArrayAdapter<String> adapter;
    List<String> list;
    ImageView back_filter;
    ProgressDialog filterprogress;
    Spinner typespiner, parkinglot;
    Button btnApplyFilter;
    EditText miniprice, maxprice, miniarea, maxarea, property_location;
    RadioGroup statusbutton;
    RadioButton forrentt, forsale;
    CheckBox newproperty, usedproperty, newproject, petroom;
    Spinner filter_city_spiner;
    ArrayList<City> cityArrayList;
    ArrayList<PropertyType> propertyTypeArrayList;
    String propertyCondition;
    String propertytypeval;
    String citystring;
    String propertystatus = "For Rent";
    String bathrooms;
    String bedrooms;
    RelativeLayout rl_mapLay, rl_dataLay;
    private ArrayList<Properties> propertiesArrayList;

    MapView mMapView;
    Double latitude, longitude;
    LocationManager lm;
    Location location;
    private GoogleMap googleMap;
    ProgressDialog delayProgresPD;


    String pet = "";
    String park = "";
//luqman

    Double lat1, lng1;
    AutoCompleteTextView autoCompleteTextView1;
    AutoCompleteAdapter adapter1;
    TextView responseView;
    PlacesClient placesClient;

    //ads
    InterstitialAd mInterstitialAd;

    public BottomSheet() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        }
    };


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to
     * install it inside the SupportMapFragment. This method will only be triggered once the
     * user has installed Google Play services and returned to the app.
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.managefiltersheet, container, false);

        rl_dataLay = view.findViewById(R.id.rl_dataLay);
        rl_mapLay = view.findViewById(R.id.rl_mapLay);


        MobileAds.initialize(getContext(), getString(R.string.admob_app_id));
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));


        String apiKey = "AIzaSyCjsvSTWSx6S79Sw10MKpmTBauZwRgraN0";
        if (apiKey.isEmpty()) {
            responseView.setText("error");
            return view;
        }

        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(getContext(), apiKey);
        }

        placesClient = Places.createClient(getContext());


        autoCompleteTextView1 = view.findViewById(R.id.auto);
        autoCompleteTextView1.setThreshold(1);
        autoCompleteTextView1.setOnItemClickListener(autocompleteClickListener);
        adapter1 = new AutoCompleteAdapter(getContext(), placesClient);
        autoCompleteTextView1.setAdapter(adapter1);

        delayProgresPD = new ProgressDialog(getContext());
        delayProgresPD.setMessage("Loading...");
        delayProgresPD.setCancelable(false);
        filterprogress = new ProgressDialog(getContext());
        filterprogress.setMessage("Loading...");
        filterprogress.setCancelable(false);
        GetCitiesList();
        GetPropertyList();
        property_location = view.findViewById(R.id.property_location);
        newproperty = view.findViewById(R.id.newproperty1);
        usedproperty = view.findViewById(R.id.usedproperty1);
        newproject = view.findViewById(R.id.newproject1);

        back_filter = view.findViewById(R.id.back_filter);

        newproperty.setChecked(false);
        newproject.setChecked(false);
        usedproperty.setChecked(false);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });


        newproject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (newproject.isChecked()) {
                    propertyCondition = "New Project";
                    newproperty.setChecked(false);
                    usedproperty.setChecked(false);
                    newproject.setChecked(true);
                }

            }
        });

        back_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
        newproperty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (newproperty.isChecked()) {

                    propertyCondition = "New";
                    usedproperty.setChecked(false);
                    newproject.setChecked(false);
                    newproperty.setChecked(true);

                }

            }
        });
        usedproperty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (usedproperty.isChecked()) {

                    propertyCondition = "Used";
                    newproperty.setChecked(false);
                    newproject.setChecked(false);
                    usedproperty.setChecked(true);

                }


            }
        });

        typespiner = view.findViewById(R.id.spinertype);
//        forRent = view.findViewById(R.id.forRent);
        forSale = view.findViewById(R.id.forsale);

//        maximumButton = view.findViewById(R.id.maximum);
//        minimumButton = view.findViewById(R.id.minimum);

        btnApplyFilter = view.findViewById(R.id.btnApplyFilter);
        BedroomAny = view.findViewById(R.id.anyButton);
        BathroomAny = view.findViewById(R.id.bathroomAny);

        cityArrayList = new ArrayList<>();
        propertyTypeArrayList = new ArrayList<>();
        filter_city_spiner = view.findViewById(R.id.filter_city_spiner);


        petroom = view.findViewById(R.id.petrooms);
        parkinglot = view.findViewById(R.id.parkings);
        oneBedroom = view.findViewById(R.id.oneBedroom);
        oneBathroom = view.findViewById(R.id.oneBathroom);
        twoBedroom = view.findViewById(R.id.twoBedrrom);
        twoBathroom = view.findViewById(R.id.twoBathroom);
        threeBedroom = view.findViewById(R.id.threeBedroom);
        threeBathroom = view.findViewById(R.id.threeBathroom);
        fourBedroom = view.findViewById(R.id.fourBedroomRoom);
        fourBathroom = view.findViewById(R.id.fourBathroom);
        enterBedroom = view.findViewById(R.id.enterBedroom);
        enterBathroom = view.findViewById(R.id.enterBathroom);
        applyFilters = view.findViewById(R.id.btnApplyFilter);

        miniprice = view.findViewById(R.id.minimumprice);
        maxprice = view.findViewById(R.id.maximumprice);

        miniarea = view.findViewById(R.id.minimumarea);
        maxarea = view.findViewById(R.id.maximumarea);
        miniprice.addTextChangedListener(new NumberTextWatcher(miniprice));
        maxprice.addTextChangedListener(new NumberTextWatcher(maxprice));
        miniarea.addTextChangedListener(new NumberTextWatcher(miniarea));
        maxarea.addTextChangedListener(new NumberTextWatcher(maxarea));

        property_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_dataLay.setVisibility(View.INVISIBLE);
                rl_mapLay.setVisibility(View.VISIBLE);

            }
        });


        petroom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (petroom.isChecked()) {
                    pet = "yes";

                } else {
                    pet = "no";
                }
            }
        });

        parkinglot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                park = parkinglot.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        filter_city_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    citystring = "";
                } else {
                    citystring = filter_city_spiner.getSelectedItem().toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        typespiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0) {
                    propertytypeval = "";
                } else {
                    propertytypeval = filter_city_spiner.getSelectedItem().toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = citystring;
                String propertytyp = propertytypeval;
                String propert_status = propertystatus;
                String location = property_location.getText().toString();
                String propertycondition = propertyCondition;
                String miniarea_val = miniprice.getText().toString();
                String maxarea_val = maxprice.getText().toString();
                String miniprice_val = miniprice.getText().toString();
                String maxprice_val = maxprice.getText().toString();
                String petroom_val = pet;
                String parking_val = park;
                String bedroom = bedrooms;
                String bath = bathrooms;


                filterData(propert_status, propertytyp, location, city, miniprice_val, maxprice_val, miniarea_val, maxarea_val, bedroom, bath, petroom_val, parking_val);

            }
        });


        newproperty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                } else {

                }
            }
        });

        usedproperty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                } else {

                }
            }
        });
        list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parkinglot.setAdapter(arrayAdapter);

        forrentt = (RadioButton) view.findViewById(R.id.forrent1);
        forsale = (RadioButton) view.findViewById(R.id.forsale1);
        statusbutton = (RadioGroup) view.findViewById(R.id.togglegroup);
        statusbutton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (forrentt.isChecked()) {
                    forrentt.setTextColor(Color.WHITE);
                    forsale.setTextColor(Color.BLACK);
                    propertystatus = "For Rent";
                }
                if (forsale.isChecked()) {
                    forsale.setTextColor(Color.WHITE);
                    forrentt.setTextColor(Color.BLACK);
                    propertystatus = "For Sale";
                }
            }
        });


        chngeButtonColor(BedroomAny, oneBedroom, twoBedroom, threeBedroom, fourBedroom, "");
        chngeButtonColor(oneBedroom, twoBedroom, threeBedroom, fourBedroom, BedroomAny, "1");
        chngeButtonColor(twoBedroom, oneBedroom, threeBedroom, fourBedroom, BedroomAny, "2");
        chngeButtonColor(threeBedroom, twoBedroom, oneBedroom, fourBedroom, BedroomAny, "3");
        chngeButtonColor(fourBedroom, twoBedroom, threeBedroom, oneBedroom, BedroomAny, "4");

        chngeButtonColor(BathroomAny, oneBathroom, twoBathroom, threeBathroom, fourBathroom, "");
        chngebathButtonColor(oneBathroom, BathroomAny, twoBathroom, threeBathroom, fourBathroom, "1");
        chngebathButtonColor(twoBathroom, BathroomAny, oneBathroom, threeBathroom, fourBathroom, "2");
        chngebathButtonColor(threeBathroom, BathroomAny, oneBathroom, twoBathroom, fourBathroom, "3");
        chngebathButtonColor(fourBathroom, BathroomAny, oneBathroom, twoBathroom, threeBathroom, "4");

        enterBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dilougeEnterBedroom();
            }
        });
        enterBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilougeEnterBathrom();
            }
        });
        applyFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Now Apply Filters", Toast.LENGTH_SHORT).show();
            }
        });
        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
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
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng arg0) {
                        // TODO Auto-generated method stub


                        Log.d("arg0", arg0.latitude + "-" + arg0.longitude);
//                        Toast.makeText(getContext(), arg0.latitude + "-" + arg0.longitude, Toast.LENGTH_SHORT).show();
                        property_location.setText(getAddres(arg0.latitude, arg0.longitude));
                        rl_mapLay.setVisibility(View.GONE);
                        rl_dataLay.setVisibility(View.VISIBLE);
                        getActivity().onBackPressed();
                    }
                });


                googleMap.setMyLocationEnabled(true);

                delayProgresPD.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        delayProgresPD.dismiss();
                    }
                }, 5000); // 3000 milliseconds delay

                lm = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                latitude = location.getLongitude();
                longitude = location.getLatitude();
                String mainlocation = (latitude + "," + longitude);

                SharedPreferences settings = getContext().getSharedPreferences("SHARED_PREFERENCES_LOCATION", Context.MODE_PRIVATE);
                settings.edit().remove("location").commit();

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(longitude, latitude);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Your Location").snippet(" current Location"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return view;
    }


    public void dilougeEnterBedroom() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customedilouge_filter_bedroom, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);

        bedrooms = editText.getText().toString();
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void dilougeEnterBathrom() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customedilouge_filter_bath, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);

        bathrooms = editText.getText().toString();
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bathrooms = editText.getText().toString();
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void GetCitiesList() {
        filterprogress.show();

        Call<GetCitiesListResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).CITYLIST_CALL();
        call.enqueue(new Callback<GetCitiesListResponse>() {
            @Override
            public void onResponse(Call<GetCitiesListResponse> call, Response<GetCitiesListResponse> response) {
                if (response.isSuccessful()) {
                    GetCitiesListResponse getCitiesListResponse = response.body();
                    if (getCitiesListResponse.getMessage() != null) {

                        if (getCitiesListResponse.getMessage().equals("all cities")) {

                            Cities_Data cities_data = response.body().getData();
                            if (cities_data != null) {
                                cityArrayList = cities_data.getCityArrayList();
                                if (cityArrayList != null) {
                                    ArrayList<String> cityList = new ArrayList<>();
                                    if (cityArrayList.size() > 0) {
                                        City city1 = new City();
                                        city1.setCity("Any city");
                                        city1.setId(1);
                                        cityArrayList.add(0, city1);

                                        for (City city : cityArrayList) {

                                            cityList.add(city.getCity());

                                        }
                                    }

                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cityList);
                                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    filter_city_spiner.setAdapter(arrayAdapter);

                                }
                            }


                        } else {
                            Toast.makeText(getContext(), "Server Error! Please try again!", Toast.LENGTH_SHORT).show();

                        }
                    }


                } else {
                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                filterprogress.dismiss();
            }

            @Override
            public void onFailure(Call<GetCitiesListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                filterprogress.dismiss();
            }
        });
    }


    public void GetPropertyList() {
        filterprogress.show();

        Call<GetpropertyListResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).PROPERTY_TYPE_LIST_CALL();
        call.enqueue(new Callback<GetpropertyListResponse>() {
            @Override
            public void onResponse(Call<GetpropertyListResponse> call, Response<GetpropertyListResponse> response) {
                if (response.isSuccessful()) {
                    GetpropertyListResponse getpropertyListResponse = response.body();
                    if (getpropertyListResponse.getMessage().equals("all property types")) {

                        PropertyType_Data propertyType_data = response.body().getData();
                        if (propertyType_data != null) {
                            propertyTypeArrayList = propertyType_data.getCityArrayList();
                            if (propertyTypeArrayList != null) {
                                ArrayList<String> propertyType = new ArrayList<>();
                                if (propertyTypeArrayList.size() > 0) {

                                    PropertyType propertyType2 = new PropertyType();
                                    propertyType2.setType("Any Type");
                                    propertyType2.setId(1);
                                    propertyTypeArrayList.add(0, propertyType2);

                                    for (PropertyType propertyType1 : propertyTypeArrayList) {
                                        propertyType.add(propertyType1.getType());
                                    }
                                }


                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, propertyType);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                typespiner.setAdapter(arrayAdapter);

                            }
                        }

                    } else {
                        Toast.makeText(getContext(), "Server Error! Please try again!", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                filterprogress.dismiss();
            }

            @Override
            public void onFailure(Call<GetpropertyListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                filterprogress.dismiss();
            }
        });
    }

    private void chngeButtonColor(Button button, Button button1, Button button2, Button button3, Button button4, String string) {


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button.setBackgroundColor(Color.parseColor("#13D7B1"));
                button1.setBackgroundColor(Color.parseColor("#DDDFEC"));
                button2.setBackgroundColor(Color.parseColor("#DDDFEC"));
                button3.setBackgroundColor(Color.parseColor("#DDDFEC"));
                button4.setBackgroundColor(Color.parseColor("#DDDFEC"));

                button.setFocusable(true);
                bedrooms = string;

            }
        });
    }

    private void chngebathButtonColor(Button button, Button button1, Button button2, Button button3, Button button4, String string) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button.setBackgroundColor(Color.parseColor("#13D7B1"));
                button1.setBackgroundColor(Color.parseColor("#DDDFEC"));
                button2.setBackgroundColor(Color.parseColor("#DDDFEC"));
                button3.setBackgroundColor(Color.parseColor("#DDDFEC"));
                button4.setBackgroundColor(Color.parseColor("#DDDFEC"));
                button.setFocusable(true);
                bathrooms = string;

            }
        });
    }


    public void filterData(String propert_status1, String propertytyp1, String location1, String city1, String miniprice_val1, String maxprice_val1, String miniarea_val1, String maxarea_val1, String bedroom1, String bath1, String petroom_val1, String parking_val1) {
        filterprogress.show();

        Call<FilterResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).FILTER_PROPERTY_CALL(propert_status1, propertytyp1, location1, city1, miniprice_val1, maxprice_val1, miniarea_val1, maxarea_val1, bedroom1, bath1, petroom_val1, parking_val1);
        call.enqueue(new Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {
                if (response.isSuccessful()) {
                    FilterResponse properties_response = response.body();
                    if (properties_response.getMessage().equals("filtered properties")) {

                        Properties_Data properties_data = response.body().getData();
                        Toast.makeText(getContext(), "Data filtered", Toast.LENGTH_SHORT).show();
                        propertiesArrayList = properties_data.getPropertiesArrayList();
                        GlobalState.getInstance().setFilteredPropertiesArrayList(propertiesArrayList);
                        GlobalState.getInstance().setFilteredOk(true);
//                        AdRequest adRequest = new AdRequest.Builder()
//                                .build();
//                        mInterstitialAd.loadAd(adRequest);
                        Fragment fragment = new Homefragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameContainer, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();


                    } else {

                        Toast.makeText(getContext(), "Data fetching error", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                filterprogress.dismiss();
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                filterprogress.dismiss();
            }
        });

    }


    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("address", strReturnedAddress.toString());
            } else {
                Log.w("address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("address", "Canont get Address!");
        }
        return strAdd;


    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private AdapterView.OnItemClickListener autocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            try {
                final AutocompletePrediction item = adapter1.getItem(i);
                String placeID = null;
                if (item != null) {
                    placeID = item.getPlaceId();
                }

//                To specify which data types to return, pass an array of Place.Fields in your FetchPlaceRequest
//                Use only those fields which are required.

                List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);

                FetchPlaceRequest request = null;
                if (placeID != null) {
                    request = FetchPlaceRequest.builder(placeID, placeFields)
                            .build();
                }

                if (request != null) {
                    placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(FetchPlaceResponse task) {
//                            responseView.setText(task.getPlace().getName() + "\n" + task.getPlace().getAddress());
//                            responseView.setText(task.getPlace().getLatLng().longitude + "\n" + task.getPlace().getLatLng().latitude);
                            lat1 = task.getPlace().getLatLng().latitude;
                            lng1 = task.getPlace().getLatLng().longitude;
                            LatLng MY_LOCATION = new LatLng(lat1, lng1);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MY_LOCATION, 17));


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            responseView.setText(e.getMessage());
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


    public String getAddres(double lat, double lng) {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getContext(), Locale.getDefault());

        String add = "";
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();

            add = city + " " + state + " " + country;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return add;
    }
}