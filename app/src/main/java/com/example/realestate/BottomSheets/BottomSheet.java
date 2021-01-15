package com.example.realestate.BottomSheets;

import android.Manifest;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.example.realestate.Utills.GlobalState;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    ProgressDialog filterprogress;
    Spinner typespiner;
    Button btnApplyFilter;
    EditText miniprice, maxprice, petroom, parkinglot, miniarea, maxarea, property_location;
    RadioGroup statusbutton;
    RadioButton forrentt, forsale;
    CheckBox newproperty, usedproperty, newproject;
    Spinner filter_city_spiner;
    ArrayList<City> cityArrayList;
    ArrayList<PropertyType> propertyTypeArrayList;
    String propertyCondition;
    String propertytypeval;
    String citystring;
    String propertystatus = "For Rent";
    String bathrooms;
    String bedrooms;
    boolean isChecked = false;
    RelativeLayout rl_mapLay, rl_dataLay;
    private ArrayList<Properties> propertiesArrayList;

    MapView mMapView;
    Double latitude, longitude;
    LocationManager lm;
    Location location;
    private GoogleMap googleMap;
    ProgressDialog delayProgresPD;


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

        delayProgresPD=new ProgressDialog(getContext());
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

        newproperty.setChecked(false);
        newproject.setChecked(false);
        usedproperty.setChecked(false);

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
        applyFilters = view.findViewById(R.id.anyButton);

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
                String petroom_val = petroom.getText().toString();
                String parking_val = parkinglot.getText().toString();
                String bedroom = bedrooms;
                String bath = bathrooms;

                /*
                * "sale_type
"property_
"location"
"city") St
"price_min
"price_max
"rating")
"area_min"
"area_max"
"bedrooms"
"bathrooms
"pets") St
"parking")*/
                filterData(propert_status, propertytyp, location, city, miniprice_val, maxprice_val, miniarea_val, maxarea_val, bedroom, bath, petroom_val, parking_val);

            }
        });


        filter_city_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
//        list = new ArrayList<String>();
//        list.add("Select one");
//        list.add("Apartamentos");
//        list.add("Edificios");
//        list.add("Solares");
//        list.add("Casas");
//        list.add("Villas");
//        list.add("Naves Industriales");
//        list.add("Fincas");
//        list.add("Local Comercial");
//
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        typespiner.setAdapter(arrayAdapter);

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

        typespiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), typespiner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        BedroomAny.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Bedroom Any", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        BathroomAny.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Bathroom Any", Toast.LENGTH_SHORT).show();
//            }
//        });
//        oneBedroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bedroom1_val = "1";
//                Toast.makeText(getContext(), "1+", Toast.LENGTH_SHORT).show();
//            }
//        });
//        oneBathroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bathroom1_val = "1";
//                Toast.makeText(getContext(), "1+", Toast.LENGTH_SHORT).show();
//            }
//        });
//        twoBedroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bedroom2_val = "2";
//                Toast.makeText(getContext(), "2+", Toast.LENGTH_SHORT).show();
//            }
//        });
//        twoBathroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bathroom2_val = "2";
//                Toast.makeText(getContext(), "2+", Toast.LENGTH_SHORT).show();
//            }
//        });
//        threeBedroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String bedroom3_val = "3";
//                Toast.makeText(getContext(), "3+", Toast.LENGTH_SHORT).show();
//            }
//        });
//        threeBathroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bathroom3_val = "3";
//                Toast.makeText(getContext(), "3+", Toast.LENGTH_SHORT).show();
//            }
//        });
//        fourBedroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bedroom4_val = "4";
//                Toast.makeText(getContext(), "4+", Toast.LENGTH_SHORT).show();
//            }
//        });
//        fourBathroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bathroom4_val = "4";
//            }
//        });
        chngeButtonColor(oneBedroom, "1");
        chngeButtonColor(twoBedroom, "2");
        chngeButtonColor(threeBedroom, "3");
        chngeButtonColor(fourBedroom, "4");

        chngebathButtonColor(oneBathroom, "1");
        chngebathButtonColor(twoBathroom, "2");
        chngebathButtonColor(threeBathroom, "3");
        chngebathButtonColor(fourBathroom, "4");

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
                        Toast.makeText(getContext(), arg0.latitude + "-" + arg0.longitude, Toast.LENGTH_SHORT).show();
                        property_location.setText(getCompleteAddressString(arg0.latitude, arg0.longitude));
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

        String etbedroom_val = editText.getText().toString();
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

        String etbathroom_val = editText.getText().toString();
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

    private void chngeButtonColor(Button button, String string) {

        isChecked = false;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = true;
                if (isChecked) {
                    button.setBackgroundColor(Color.parseColor("#13D7B1"));
                    button.setFocusable(true);
                    bedrooms = string;
                    Toast.makeText(getContext(), bedrooms + "bedrooms", Toast.LENGTH_SHORT).show();
                } else {
                    isChecked = false;
                    button.setBackgroundColor(Color.parseColor("#DDDFEC"));
                }
            }
        });
    }

    private void chngebathButtonColor(Button button, String string) {

        isChecked = false;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = true;
                if (isChecked) {
                    button.setBackgroundColor(Color.parseColor("#13D7B1"));
                    button.setFocusable(true);
                    bathrooms = string;
                    Toast.makeText(getContext(), bedrooms + "bathrooms", Toast.LENGTH_SHORT).show();
                } else {
                    isChecked = false;
                    button.setBackgroundColor(Color.parseColor("#DDDFEC"));
                }
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


}