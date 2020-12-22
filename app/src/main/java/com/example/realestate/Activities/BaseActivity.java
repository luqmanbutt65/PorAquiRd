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
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;


public class BaseActivity extends AppCompatActivity {

    Context bContext;
    Activity bActivity;

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
    public void onStart() {
        super.onStart();
        //startService(new Intent(bContext,MyLogOutService.class));
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
}