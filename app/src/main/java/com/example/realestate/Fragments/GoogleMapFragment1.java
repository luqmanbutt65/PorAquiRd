package com.example.realestate.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.AutoCompleteAdapter;
import com.example.realestate.Utills.GlobalState;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

public class GoogleMapFragment1 extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMyLocationButtonClickListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private final int REQUEST_LOCATION_PERMISSION = 1;
    GoogleMapFragment1 googleMapFragment1;
    MapView mMapView;
    ArrayList<Properties> propertiesArrayList = new ArrayList<>();
    LocationManager locationManager;
    Location location;
    String provider;
    Double latitud, longitud;
    TextView city, property_location, park, bath, area, price, rating;
    ImageView main_image;
    ProgressDialog mapprogressDialog;
    Double lat, lng;


    //luqman
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteAdapter adapter;
    TextView responseView;
    PlacesClient placesClient;
    private GoogleMap mMap;
    private AdapterView.OnItemClickListener autocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            try {
                final AutocompletePrediction item = adapter.getItem(i);
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
                            lat = task.getPlace().getLatLng().latitude;
                            lng = task.getPlace().getLatLng().longitude;
                            LatLng MY_LOCATION = new LatLng(lat, lng);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MY_LOCATION, 17));


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

    public GoogleMapFragment1() {
        // Required empty public constructor
    }

    public GoogleMapFragment1 getInstance() {
        if (googleMapFragment1 == null) {
            googleMapFragment1 = new GoogleMapFragment1();
        }
        return googleMapFragment1;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        checkLocationPermission();
        // Inflate the layout for this fragment
        mapprogressDialog = new ProgressDialog(getContext());
        mapprogressDialog.setMessage("Loading ...");
        mapprogressDialog.setCancelable(false);
        View view = inflater.inflate(R.layout.fragment_google_map1, container, false);

//        responseView = view.findViewById(R.id.response);

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


        autoCompleteTextView = view.findViewById(R.id.auto);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(autocompleteClickListener);
        adapter = new AutoCompleteAdapter(getContext(), placesClient);
        autoCompleteTextView.setAdapter(adapter);

        if (GlobalState.getInstance().getPropertiesArrayList() != null) {

            if (GlobalState.getInstance().getPropertiesArrayList().size() > 0) {

                for (Properties properties : GlobalState.getInstance().getPropertiesArrayList()) {
                    propertiesArrayList.add(properties);

                }

            }
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.main_branch_map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        location = getLastKnownLocation();
        if (location != null) {
            latitud = location.getLongitude();
            longitud = location.getLatitude();
        }

        return view;
    }

    public void onBackPressed() {
        ask_exit();

    }

    public void ask_exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.exit_title));
        builder.setMessage(getString(R.string.exit_subtitle));
        builder.setCancelable(true);

        // Action if user selects 'yes'
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        // Actions if user selects 'no'
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        // Create the alert dialog using alert dialog builder
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        // Finally, display the dialog when user press back button
        dialog.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            checkLocationPermission();
            return;
        }

        mMap.setMyLocationEnabled(true);
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        location = getLastKnownLocation();

        if (location != null) {
            latitud = location.getLongitude();
            longitud = location.getLatitude();
        }
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public View getInfoWindow(Marker marker) {
                mapprogressDialog.show();

                View view = ((getActivity())).getLayoutInflater().inflate(R.layout.map_container, null);

                city = view.findViewById(R.id.citi);
                property_location = view.findViewById(R.id.property_location);
                park = view.findViewById(R.id.parking);
                bath = view.findViewById(R.id.bath);
                area = view.findViewById(R.id.area);
                price = view.findViewById(R.id.property_price);
                rating = view.findViewById(R.id.property_reviews);
                main_image = view.findViewById(R.id.property_main_image);

                Properties currentMarkerPropertites = null;
                String titleTemp = marker.getTitle();
                for (Properties properties : GlobalState.getInstance().getPropertiesArrayList()) {
                    if (properties.getTitle().equals(titleTemp)) {
                        currentMarkerPropertites = properties;
                    }
                }

                if (currentMarkerPropertites.getPropertiesExtraArrayList() != null) {

                    if (currentMarkerPropertites.getPropertiesExtraArrayList().size() > 0) {
                        for (int i = 0; i < currentMarkerPropertites.getPropertiesExtraArrayList().size(); i++) {
                            if (i == 3) {
                                if (currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getType().equals("parking")) {
                                    park.setText(currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getQuantity());
                                    //currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getType() + " " +
                                } else {
                                    park.setText(currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getQuantity());
                                    //currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getType() + " " +
                                }
                            } else if (i == 0) {
                                if (currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getType().equals("bathrooms")) {
                                    bath.setText(currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getQuantity());
                                    //currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getType() + " " +
                                } else {
                                    bath.setText(currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getQuantity());
                                    //currentMarkerPropertites.getPropertiesExtraArrayList().get(i).getType() + " " +

                                }
                            }
                        }
                    } else {
                        park.setText("N/A");

                        bath.setText("N/A");
                    }


                } else {
                    park.setText("N/A");

                    bath.setText("N/A");
                }


                String area_val = ((area_val = currentMarkerPropertites.getArea())) != null ? area_val : "N/A";
                area.setText(area_val + "M\u00B2");

                String town_val = ((town_val = currentMarkerPropertites.getLocation()) != null) ? town_val : "N/A";
                property_location.setText(town_val);

                String city_val = ((city_val = currentMarkerPropertites.getCity()) != null) ? city_val : "N/A";
                city.setText(city_val);

                String review_val = ((review_val = currentMarkerPropertites.getRating()) != null) ? review_val : "N/A";
                rating.setText(review_val);

                String price_val = ((price_val = String.valueOf(currentMarkerPropertites.getPrice())) != null) ? price_val : "N/A";
                price.setText("$ " + price_val);


                Glide.with(getContext()).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + currentMarkerPropertites.getMain_image()).into(main_image);
                mapprogressDialog.dismiss();
                return view;

            }

            //content wraping
            public View getInfoContents(Marker arg0) {
                return null;
            }
        });


        String longitudee = String.valueOf(longitud);
        String latitude = String.valueOf(latitud);
        LatLng MY_LOCATION = new LatLng(latitud, longitud);
        new SharedPreferenceConfig().savelongitudeInSP("longitude", longitudee, getContext());
        new SharedPreferenceConfig().savelatitudeInSP("latitude", latitude, getContext());


        for (int i = 0; i < propertiesArrayList.size(); i++) {
            createMarker(propertiesArrayList.get(i).getId(), propertiesArrayList.get(i).getLatitude(), propertiesArrayList.get(i).getLongitude(), propertiesArrayList.get(i).getTitle(), mMap);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MY_LOCATION, 17));
        SharedPreferences settings = getContext().getSharedPreferences("SHARED_PREFERENCES_LOCATION", Context.MODE_PRIVATE);
        settings.edit().remove("location").commit();


        // For dropping a marker at a point on the Map
        LatLng sydney = new LatLng(longitud, latitud);
        //  mMap.addMarker(new MarkerOptions().position(sydney).title("Your Location").snippet(" current Location"));
        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    protected Marker createMarker(int propertyId, double latitude, double longitude, String title, GoogleMap googleMap) {
        return googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet("ABC123")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.houses)));
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getContext())
                        .setTitle("Location!")
                        .setMessage("Allow Location?")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        // EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //    @Override
//    protected void onResume() {
//        super.onResume();
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            locationManager.requestLocationUpdates(provider, 400, 1, this);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            locationManager.removeUpdates(this);
//        }
//    }
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
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

    private Location getLastKnownLocation() {
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                checkLocationPermission();

            }
            location = locationManager.getLastKnownLocation(provider);
            if (location == null) {
                continue;
            }
            if (bestLocation == null || location.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = location;
            }
        }
        return bestLocation;
    }


}