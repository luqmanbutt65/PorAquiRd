package com.example.realestate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class tep {

    @SerializedName("Status")
    @Expose
    private String city;

    public ArrayList<DashboardData> getMdatasource() {
        return mdatasource;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMdatasource(ArrayList<DashboardData> mdatasource) {
        this.mdatasource = mdatasource;
    }

    @SerializedName("data")
    @Expose
    private ArrayList<DashboardData> mdatasource;
}
