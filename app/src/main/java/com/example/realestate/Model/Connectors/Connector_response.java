package com.example.realestate.Model.Connectors;

import com.example.realestate.Model.GetList.Cities_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Connector_response {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Connector_Data data;

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

    public Connector_Data getData() {
        return data;
    }

    public void setData(Connector_Data data) {
        this.data = data;
    }
}
