package com.example.realestate.Model.MyProject;

import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddProperties_Response {
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

