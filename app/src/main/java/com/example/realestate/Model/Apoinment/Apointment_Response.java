package com.example.realestate.Model.Apoinment;

import com.example.realestate.Model.GetList.Cities_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apointment_Response {


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;


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
}
