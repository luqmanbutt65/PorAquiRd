package com.example.realestate.Model.Connectors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Connector_Data {
    @SerializedName("connectors")
    @Expose
    private ArrayList<Connectors> connectorsArrayList;

    public ArrayList<Connectors> getConnectorsArrayList() {
        return connectorsArrayList;
    }

    public void setConnectorsArrayList(ArrayList<Connectors> connectorsArrayList) {
        this.connectorsArrayList = connectorsArrayList;
    }
}
