package com.example.realestate.Model.GetList;

import com.example.realestate.Model.Like.PropertiesLike_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCitiesListResponse {


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Cities_Data data;

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

    public Cities_Data getData() {
        return data;
    }

    public void setData(Cities_Data data) {
        this.data = data;
    }
}
