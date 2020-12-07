package com.example.realestate.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.realestate.R;
import com.example.realestate.Splash.FirstActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permisions extends AppCompatActivity {
    Button restart;
    Boolean isRationale;
    Boolean isFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisions);
        restart = findViewById(R.id.restartbtn);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Permisions.this, FirstActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askPermissions(true);
        } else {
            startActivity(new Intent(Permisions.this, MainActivity.class));
            finish();
        }

    }

    private void askPermissions(boolean isForOpen) {
        isRationale = false;
        List permissionsRequired = new ArrayList();

        final List<String> permissionsList = new ArrayList<String>();
        if (!checkPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsRequired.add("Write External Storage");
        if (!checkPermission(permissionsList, Manifest.permission.CALL_PHONE))
            permissionsRequired.add("Call phone");
        if (!checkPermission(permissionsList, Manifest.permission.READ_PHONE_STATE))
            permissionsRequired.add("Read phone state");
        if (!checkPermission(permissionsList, Manifest.permission.READ_CONTACTS))
            permissionsRequired.add("Read Contacts");
        if (!checkPermission(permissionsList, Manifest.permission.RECEIVE_SMS))
            permissionsRequired.add("Receive SMS");
        if (!checkPermission(permissionsList, Manifest.permission.GET_ACCOUNTS))
            permissionsRequired.add("Get Accounts");
        if (!checkPermission(permissionsList, Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsRequired.add("Location");
        if (!checkPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsRequired.add("Location");

        if (permissionsList.size() > 0 && !isRationale) {
            if (permissionsRequired.size() > 0) {

            }
            if (isForOpen) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ActivityCompat.requestPermissions(this, permissionsList.toArray(new String[permissionsList.size()]),
                            11);
                }
            }

        } else if (isRationale) {
            if (isForOpen) {

                new AlertDialog.Builder(this)
                        .setTitle("Permission Alert")
                        .setMessage("You need to grant permissions manually. Go to permission and grant all permissions.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, 123);
                            }
                        })
                        .show();
            }
        } else {
            startActivity(new Intent(Permisions.this, MainActivity.class));
            finish();
        }
    }

    private boolean checkPermission(List permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!isFirst) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        isRationale = true;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 11:
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CALL_PHONE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_CONTACTS, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.RECEIVE_SMS, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);

                // Fill with results
                for (int i = 0; i < permissions.length; i++) {
                    perms.put(permissions[i], grantResults[i]);
                }
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    // All Permissions Granted
                    startActivity(new Intent(Permisions.this, MainActivity.class));
                    finish();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "Some Permission is Denied.", Toast.LENGTH_SHORT)
                            .show();
                    isFirst = false;
                    askPermissions(true);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }
}