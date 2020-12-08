package com.example.realestate.Model.REST.PropertiesSingle;

import com.example.realestate.Model.REST.Properties.Properties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertiesSingleResp {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private PropertiesSIngleData propertiesData;

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

    public PropertiesSIngleData getPropertiesData() {
        return propertiesData;
    }

    public void setPropertiesData(PropertiesSIngleData propertiesData) {
        this.propertiesData = propertiesData;
    }
}
