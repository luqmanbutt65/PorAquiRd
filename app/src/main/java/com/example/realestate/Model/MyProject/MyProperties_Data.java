package com.example.realestate.Model.MyProject;

import com.example.realestate.Model.REST.Properties.Properties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyProperties_Data {
    @SerializedName("user_properties")
    @Expose
    private ArrayList<Properties> propertiesArrayList;

    public ArrayList<Properties> getPropertiesArrayList() {
        return propertiesArrayList;
    }

    public void setPropertiesArrayList(ArrayList<Properties> propertiesArrayList) {
        this.propertiesArrayList = propertiesArrayList;
    }
}
