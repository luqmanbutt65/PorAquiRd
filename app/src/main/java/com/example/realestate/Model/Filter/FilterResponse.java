package com.example.realestate.Model.Filter;

import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterResponse {


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Properties_Data data;

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

    public Properties_Data getData() {
        return data;
    }

    public void setData(Properties_Data data) {
        this.data = data;
    }
}
