package com.example.realestate.Model.Plans.Purchasedplan;

import com.example.realestate.Model.Plans.Plan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PurchasedPlanResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private PurchasedplanData data;

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

    public PurchasedplanData getData() {
        return data;
    }

    public void setData(PurchasedplanData data) {
        this.data = data;
    }
}
