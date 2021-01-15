package com.example.realestate.Model.Plans;

import com.example.realestate.Model.GetUpdateData.UpdateData_data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlanResponse {

    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<Plan> data;

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

    public ArrayList<Plan> getData() {
        return data;
    }

    public void setData(ArrayList<Plan> data) {
        this.data = data;
    }
}
