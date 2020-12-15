package com.example.realestate.Model.MyProject;

import com.example.realestate.Model.Like.PropertiesLike_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyProperties_Response {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private MyProperties_Data data;

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

    public MyProperties_Data getData() {
        return data;
    }

    public void setData(MyProperties_Data data) {
        this.data = data;
    }
}

