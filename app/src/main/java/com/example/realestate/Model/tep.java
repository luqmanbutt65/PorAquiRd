package com.example.realestate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class tep {

    @SerializedName("Status")
    @Expose
    private String city;
    @SerializedName("data")
    @Expose
    private ArrayList<DashboardData> mdatasource;

    public ArrayList<DashboardData> getMdatasource() {
        return mdatasource;
    }

    public void setMdatasource(ArrayList<DashboardData> mdatasource) {
        this.mdatasource = mdatasource;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
