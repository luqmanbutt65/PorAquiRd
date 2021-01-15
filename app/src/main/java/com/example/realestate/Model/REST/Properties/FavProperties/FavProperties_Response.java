package com.example.realestate.Model.REST.Properties.FavProperties;

import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class FavProperties_Response extends RealmObject {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private FavProperties_Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FavProperties_Data getData() {
        return data;
    }

    public void setData(FavProperties_Data data) {
        this.data = data;
    }
}

