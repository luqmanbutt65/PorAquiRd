package com.example.realestate.Utills;


import android.app.Application;

import com.example.realestate.Model.Connectors.connectorData.ConnectorData_Data;
import com.example.realestate.Model.GetUpdateData.User;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.Rating.GetRating.User_Reviews;
import com.example.realestate.Model.UserInfo;

import java.util.ArrayList;


public class GlobalState extends Application {

    private static GlobalState mInstance;
    Properties_Data properties_data;
    private String current_Property_id;
    private String current_appointment_id;
    private boolean isFilteredOk=false;




    public boolean isFilteredOk() {
        return isFilteredOk;
    }

    public void setFilteredOk(boolean filteredOk) {
        isFilteredOk = filteredOk;
    }

    public ArrayList<Properties> getFilteredPropertiesArrayList() {
        return filteredPropertiesArrayList;
    }

    public void setFilteredPropertiesArrayList(ArrayList<Properties> filteredPropertiesArrayList) {
        this.filteredPropertiesArrayList = filteredPropertiesArrayList;
    }

    private ArrayList<Properties> propertiesArrayList;
    private ArrayList<Properties> filteredPropertiesArrayList;
    private UserInfo userInfo;
    private ConnectorData_Data connectorData_data;
    private User user;
    private ArrayList<User_Reviews> user_reviews;
    private String lattitude;
    private String longitude;

    public ConnectorData_Data getConnectorData_data() {
        return connectorData_data;
    }

    public void setConnectorData_data(ConnectorData_Data connectorData_data) {
        this.connectorData_data = connectorData_data;
    }

    public static GlobalState getmInstance() {
        return mInstance;
    }

    public static void setmInstance(GlobalState mInstance) {
        GlobalState.mInstance = mInstance;
    }

    public static synchronized GlobalState getInstance() {
        if (mInstance == null) {
            mInstance = new GlobalState();
        }
        return mInstance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getCurrent_appointment_id() {
        return current_appointment_id;
    }

    public void setCurrent_appointment_id(String current_appointment_id) {
        this.current_appointment_id = current_appointment_id;
    }

    public String getCurrent_Property_id() {
        return current_Property_id;
    }

    public void setCurrent_Property_id(String current_Property_id) {
        this.current_Property_id = current_Property_id;
    }

    public ArrayList<User_Reviews> getUser_reviews() {
        return user_reviews;
    }

    public void setUser_reviews(ArrayList<User_Reviews> user_reviews) {
        this.user_reviews = user_reviews;
    }

    public Properties_Data getProperties_data() {
        return properties_data;
    }

    public void setProperties_data(Properties_Data properties_data) {
        this.properties_data = properties_data;
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

    public void clearData() {
//mInstance.merchantDetail=null;
    }


}