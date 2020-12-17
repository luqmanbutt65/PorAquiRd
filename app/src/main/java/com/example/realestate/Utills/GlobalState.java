package com.example.realestate.Utills;


import android.app.Application;

import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.UserInfo;

import java.util.ArrayList;


public class GlobalState extends Application {

    private static GlobalState mInstance;
    private ArrayList<Properties> propertiesArrayList;

    private UserInfo userInfo;

    Properties_Data properties_data;

    public Properties_Data getProperties_data() {
        return properties_data;
    }

    public void setProperties_data(Properties_Data properties_data) {
        this.properties_data = properties_data;
    }

    public static GlobalState getmInstance() {
        return mInstance;
    }

    public static void setmInstance(GlobalState mInstance) {
        GlobalState.mInstance = mInstance;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public ArrayList<Properties> getPropertiesArrayList() {
        return propertiesArrayList;
    }

    public void setPropertiesArrayList(ArrayList<Properties> propertiesArrayList) {
        this.propertiesArrayList = propertiesArrayList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized GlobalState getInstance() {
        if (mInstance == null) {
            mInstance = new GlobalState();
        }
        return mInstance;
    }

    public void clearData() {
//mInstance.merchantDetail=null;
    }


}