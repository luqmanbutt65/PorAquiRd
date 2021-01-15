package com.example.realestate.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.AppConstant;
import com.example.realestate.Fragments.Apointments_Tab;
import com.example.realestate.Fragments.GoogleMapFragment1;
import com.example.realestate.Fragments.MyApointmentsFragment;
import com.example.realestate.Fragments.MyFavrotFragment;
import com.example.realestate.Fragments.PrivecyPolicy;
import com.example.realestate.Fragments.ProfileFragment;
import com.example.realestate.Fragments.Homefragment;
import com.example.realestate.Fragments.MapsFragment;
import com.example.realestate.Fragments.MyProjectsFragment;
import com.example.realestate.Fragments.TermConditions;
import com.example.realestate.Model.MyProject.AddProperties_Response;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Model.Notification;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.Model.Token_response.Send_Token_Response;
import com.example.realestate.R;
import com.example.realestate.Registration.LoginScreen;
import com.example.realestate.Registration.OTPScreenResetPass;
import com.example.realestate.Registration.resetpassword;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.paypal.android.sdk.payments.PayPalService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {
    private static final String AUTH_KEY = "AAAAsYzxVcs:APA91bG5mUGaYeW9K_6hVtZGX6wBKHxy5vZ7C8zYktYeXqg0E1jkw6OHt8q5PGSIyCpUDZZu3thsnbon5fIS3AoXKaC--UtrTKoL0K1PWSRPKtIxj9cWmmwaOMeN3GfGDGgedKSM52Ia";

    BottomNavigationView bottomNavigationView;
    Fragment temp;
    String TAG = "MainActivity";
    String token2;
    boolean isGPS = false;
    ProgressDialog mainprogressdilouge;

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

    }

    @Override
    public void onDestroy() {
        // Stop service when done
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notification();

        mainprogressdilouge = new ProgressDialog(MainActivity.this);
        mainprogressdilouge.setMessage("Logining..."); // Setting Message
        mainprogressdilouge.setCancelable(false);


        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
//        showToast(macAddress);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new Homefragment()).commit();
        sendWithOtherThread("token");
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomappbar);
        // FirebaseMessaging.getInstance().subscribeToTopic("news");
        sendFCMTokenToServer();
        //BottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int size = bottomNavigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    bottomNavigationView.getMenu().getItem(i).setChecked(false);
                }
                switch (item.getItemId()) {
                    case R.id.home:
                        temp = new Homefragment();
                        callFreg(temp);
                        item.setChecked(true);
                        break;
                    case R.id.location:
                        if (!statusCheck()) {
                            showToast("GPS Not On");
                        } else {
                            mainprogressdilouge.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    temp = new GoogleMapFragment1();
                                    callFreg(temp);
                                    item.setChecked(true);
                                    mainprogressdilouge.dismiss();
                                }
                            }, 3000);

//                            Thread thread = new Thread() {
//
//                                @Override
//                                public void run() {
//                                    try {
//                                        sleep(7000);
//                                        mainprogressdilouge.show();
//
//                                        temp = new GoogleMapFragment1();
//                                        callFreg(temp);
//                                        item.setChecked(true);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    super.run();
//                                }
//                            };
//                            thread.start();
//                            mainprogressdilouge.dismiss();
                        }

                        break;
                    case R.id.likes:

//                        String user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", MainActivity.this);
//                        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", MainActivity.this)) {
//                            if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", MainActivity.this)
//                                    != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", MainActivity.this) != null) {
                        temp = new MyFavrotFragment();
                        callFreg(temp);
                        item.setChecked(true);
//                            }
//                        } else {
//                            showToast("You are Not Logged in");
//
//                        }

                        break;
                    case R.id.booking:

                        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", MainActivity.this)) {
                            if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", MainActivity.this)
                                    != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", MainActivity.this) != null) {
                                item.setChecked(false);
                                temp = new Apointments_Tab();
                                callFreg(temp);
                            }
                        } else {
                            showToast("You are Not Logged in");

                        }
                        break;


                    case R.id.profile:
                        if (new SharedPreferenceConfig().getBooleanFromSP(Common.ISLOGIN, MainActivity.this)) {
                            temp = new ProfileFragment();
                            callFreg(temp);
                            item.setChecked(true);
                        } else {

                            dilougLogin();

                        }


                        break;
                }
                return true;
            }
        });

    }

    private void callFreg(Fragment temp1) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, temp1).commit();

    }

    public void dilougLogin() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(MainActivity.this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.loginpopup, null);


        RelativeLayout cancel = dialogView.findViewById(R.id.cancel);
        RelativeLayout login = dialogView.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                startActivity(intent);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void notification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void sendWithOtherThread(final String type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                pushNotification(type);
            }
        }).start();
    }

    private void pushNotification(String type) {
        JSONObject jPayload = new JSONObject();
        JSONObject jNotification = new JSONObject();
        JSONObject jData = new JSONObject();
        try {
            jNotification.put("title", "Google I/O 2016");
            jNotification.put("body", "Firebase Cloud Messaging (App)");
            jNotification.put("sound", "default");
            jNotification.put("badge", "1");
            jNotification.put("click_action", "OPEN_ACTIVITY_1");
            jNotification.put("icon", "ic_notification");

            jData.put("picture", "https://miro.medium.com/max/1400/1*QyVPcBbT_jENl8TGblk52w.png");

            switch (type) {
                case "tokens":
                    JSONArray ja = new JSONArray();
                    ja.put("c5pBXXsuCN0:APA91bH8nLMt084KpzMrmSWRS2SnKZudyNjtFVxLRG7VFEFk_RgOm-Q5EQr_oOcLbVcCjFH6vIXIyWhST1jdhR8WMatujccY5uy1TE0hkppW_TSnSBiUsH_tRReutEgsmIMmq8fexTmL");
                    ja.put(token2);
                    jPayload.put("registration_ids", ja);
                    break;
                case "topic":
                    jPayload.put("to", "/topics/news");
                    break;
                case "condition":
                    jPayload.put("condition", "'sport' in topics || 'news' in topics");
                    break;
                default:
                    jPayload.put("to", token2);
            }

            jPayload.put("priority", "high");
            jPayload.put("notification", jNotification);
            jPayload.put("data", jData);

            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", AUTH_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Send FCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jPayload.toString().getBytes());

            // Read FCM response.
            InputStream inputStream = conn.getInputStream();
            final String resp = convertStreamToString(inputStream);

            Handler h = new Handler(Looper.getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {
//                    mTextView.setText(resp);
                }
            });
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }


    private void sendUserToken(String user_id, String token) {
//        AddDataProgressDialog.show();
        Call<Send_Token_Response> call = ApiClient.getRetrofit().create(ApiInterface.class).SEND_TOKEN_CALL(user_id, token);
        call.enqueue(new Callback<Send_Token_Response>() {
            @Override
            public void onResponse(Call<Send_Token_Response> call, Response<Send_Token_Response> response) {
                if (response.isSuccessful()) {
                    Send_Token_Response send_token_response = response.body();
                    if (send_token_response.getMessage().equals("token saved successfully")) {

//                        showToast("Token sended Added Succesfully");

                    } else {

//                        Toast.makeText(getApplicationContext(), "Token Uploading error", Toast.LENGTH_SHORT).show();
                    }

                } else {

//                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
//                AddDataProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Send_Token_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                AddDataProgressDialog.dismiss();
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        //startService(new Intent(bContext,MyLogOutService.class));
        sendFCMTokenToServer();

    }

    @Override
    protected void onResume() {
        super.onResume();
        sendFCMTokenToServer();
        statusCheck2();
    }

    private void sendFCMTokenToServer() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        token2 = task.getResult();

                        // Log and toast
                        String msg = token2;
                        Log.d(TAG, msg);
                        String user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", MainActivity.this);
                        String token1 = token2;
                        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", MainActivity.this)) {
                            if (new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", MainActivity.this)
                                    != null && new SharedPreferenceConfig().getPasswordOfUSerFromSP("Password", MainActivity.this) != null) {
                                sendUserToken(user_Id, token1);
                            }
                        } else {
//                            showToast("You are Not Logged in");
                        }
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private Context getActContext() {
        return MainActivity.this;
    }

    public void getCurrentLocation() {

        final LocationRequest locationRequest = new LocationRequest();

        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(MainActivity.this).removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestlocationIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestlocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestlocationIndex).getLongitude();

//                            sp.saveStringValue("latitude", String.valueOf(latitude));
//                            sp.saveStringValue("longitude", String.valueOf(longitude));
                        }

                    }
                }, Looper.myLooper());

    }

    public void statusCheck2() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                getCurrentLocation();
            }
            //st.toast("Enabled");

        }
    }

    public boolean statusCheck() {
        final LocationManager manager = (LocationManager) MainActivity.this.getSystemService(LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            statusCheck2();
            return false;

        } else {
            return true;
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstant.GPS_REQUEST) {
                isGPS = true; // flag maintain before get location
            }
        }
    }
}

