package com.example.realestate.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
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

import java.util.ArrayList;

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
    private GoogleMap mMap;

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
        View view = inflater.inflate(R.layout.fragment_google_map1, container, false);
        propertiesArrayList.add(new Properties(1, "House1", 31.4718, 74.3546));
        propertiesArrayList.add(new Properties(2, "House2", 31.505284, 74.331989));
        propertiesArrayList.add(new Properties(3, "House3", 31.553319, 74.338861));
        propertiesArrayList.add(new Properties(3, "House4", 31.579210, 74.304342));
        GlobalState.getInstance().setPropertiesArrayList(propertiesArrayList);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.main_branch_map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        //   Log.e("WhataPro---",provider);
        //   checkLocationPermission();


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
            return;
        }

        mMap.setMyLocationEnabled(true);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        latitud = location.getLongitude();
        longitud = location.getLatitude();
        String mainlocation = (latitud + "," + longitud);
        LatLng MY_LOCATION = new LatLng(latitud, longitud);
        for (int i = 0; i < propertiesArrayList.size(); i++) {
            createMarker(propertiesArrayList.get(i).getLatitude(), propertiesArrayList.get(i).getLongitude(), propertiesArrayList.get(i).getTitle(), mMap);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MY_LOCATION, 17));
        SharedPreferences settings = getContext().getSharedPreferences("SHARED_PREFERENCES_LOCATION", Context.MODE_PRIVATE);
        settings.edit().remove("location").commit();

        new SharedPreferenceConfig().saveLocationOfUSerInSP("location", mainlocation, getContext());

        // For dropping a marker at a point on the Map
        LatLng sydney = new LatLng(longitud, latitud);
        //  mMap.addMarker(new MarkerOptions().position(sydney).title("Your Location").snippet(" current Location"));

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    protected Marker createMarker(double latitude, double longitude, String title, GoogleMap googleMap) {
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


}