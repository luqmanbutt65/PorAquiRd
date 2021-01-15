package com.example.realestate.Activities;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import com.example.realestate.AppConstant;
import com.example.realestate.Utills.GlobalState;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity:Loc";
    Context bContext;
    Activity bActivity;
    double lat, lon;
    private boolean isLocFetch = false;
    private Location currentLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private int locationRequestCode = 1000;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    // private boolean isContinue = false;
    private boolean isGPS = false;

    public static boolean toBooleanDefaultIfNull(Boolean bool) {
        if (bool == null) return false;
        return bool.booleanValue();
    }

    public static File savebitmap(Bitmap bmp, String fileName) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + fileName + ".jpg");
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
        return f;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // stopService(new Intent(bContext,MyLogOutService.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();


    }

    private void initView() {
        bContext = this;
        bActivity = this;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(bContext);

    }

    public void showToast(String message) {
        Toast.makeText(bContext, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isEmailValid(CharSequence email) {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean is2EmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public String excatFigure(double value) {
        BigDecimal d = new BigDecimal(String.valueOf(value));
        return d.toPlainString();
    }

    public String getUnixTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        String uNixtimeStamp = tsLong.toString();
        return uNixtimeStamp;
    }

//
//    public void getLocation() {
//        if (ActivityCompat.checkSelfPermission(bContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(bContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(bActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                    AppConstant.LOCATION_REQUEST);
//
//        } else {
//
//            mFusedLocationClient.getLastLocation().addOnSuccessListener(bActivity, new OnSuccessListener<Location>() {
//                @SuppressLint("MissingPermission")
//                @Override
//                public void onSuccess(Location location) {
//                    if (location != null) {
//                        wayLatitude = location.getLatitude();
//                        wayLongitude = location.getLongitude();
//                        lat=wayLatitude;
//                        lon=wayLongitude;
//                        //isLocFetch=true;
//                        setLocationFetch(true);
//                        Log.e(TAG,"Lattitude:"+wayLatitude+" Longitude: "+wayLongitude);
//                        GlobalState.getInstance().setLattitude(String.valueOf(wayLatitude));
//                        GlobalState.getInstance().setLongitude(String.valueOf(wayLongitude));
//
//                        //showToast(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
//                        //  txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
//                    } else {
//                        //isLocFetch=false;
//                        setLocationFetch(false);
//                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
//                    }
//                }
//            });
////            }
//        }
//    }
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 1000: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
////                    if (isContinue) {
////                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
////                    } else {
//                    mFusedLocationClient.getLastLocation().addOnSuccessListener(bActivity, new OnSuccessListener<Location>() {
//                        @Override
//                        public void onSuccess(Location location) {
//                            if (location != null) {
//                                wayLatitude = location.getLatitude();
//                                wayLongitude = location.getLongitude();
//                                lat=wayLatitude;
//                                lon=wayLongitude;
//                                setLocationFetch(true);
//                                //isLocFetch=true;
//                                Log.e(TAG,"Lattitude:"+wayLatitude+" Longitude: "+wayLongitude);
//                                GlobalState.getInstance().setLattitude(String.valueOf(wayLatitude));
//                                GlobalState.getInstance().setLongitude(String.valueOf(wayLongitude));
//                                // showToast(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
//                                //txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
//                            } else {
//                                //isLocFetch=false;
//                                setLocationFetch(false);
//                                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
//                            }
//                        }
//                    });
////                    }
//                } else {
//                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == AppConstant.GPS_REQUEST) {
//                isGPS = true; // flag maintain before get location
//            }
//        }
//    }
//    public void setLocationFetch(boolean state)
//    {
//        isLocFetch=state;
//    }


    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(BaseActivity.this, Locale.getDefault());
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

}