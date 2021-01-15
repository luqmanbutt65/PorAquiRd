package com.example.realestate.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realestate.R;
import com.example.realestate.SetMapdataInterface;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;
import com.example.realestate.Utills.MyService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {
    MapView mMapView;
    Double latitude, longitude;
    LocationManager lm;
    Location location;
    SetMapdataInterface setMapdataInterface;
    private GoogleMap googleMap;

    ProgressDialog delayProgresPD;



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

    public MapsFragment(SetMapdataInterface setMapdataInterface) {
        this.setMapdataInterface = setMapdataInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);


        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        delayProgresPD=new ProgressDialog(getContext());
        delayProgresPD.setMessage("Loading...");
        delayProgresPD.setCancelable(false);

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

                        setMapdataInterface.onclick(arg0.latitude, arg0.longitude);

                        Log.d("arg0", arg0.latitude + "-" + arg0.longitude);
                        Toast.makeText(getContext(), arg0.latitude + "-" + arg0.longitude, Toast.LENGTH_SHORT).show();


                        getActivity().onBackPressed();
                    }
                });



                googleMap.setMyLocationEnabled(true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        delayProgresPD.dismiss();
                    }
                }, 5000); // 3000 milliseconds delay
                lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
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
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        int X = (int)event.getX();
//        int Y = (int)event.getY();
//
//        GeoPoint geoPoint = mMapView.getProjection().fromPixels(X, Y);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


}