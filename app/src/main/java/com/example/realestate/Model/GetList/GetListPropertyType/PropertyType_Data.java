package com.example.realestate.Model.GetList.GetListPropertyType;

import com.example.realestate.Model.GetList.City;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PropertyType_Data {


    @SerializedName("property_types")
    @Expose
    private ArrayList<PropertyType> cityArrayList;

    public ArrayList<PropertyType> getCityArrayList() {
        return cityArrayList;
    }

    public void setCityArrayList(ArrayList<PropertyType> cityArrayList) {
        this.cityArrayList = cityArrayList;
    }
}
