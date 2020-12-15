package com.example.realestate.Model.REST.Properties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties_Add_Response {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private AddPropertiesData data;

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

    public AddPropertiesData getData() {
        return data;
    }

    public void setData(AddPropertiesData data) {
        this.data = data;
    }
}

