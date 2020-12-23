package com.example.realestate.Model.Apoinment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Get_Apointment_Response {


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Apointment_Data apointment_data;

    public Apointment_Data getApointment_data() {
        return apointment_data;
    }

    public void setApointment_data(Apointment_Data apointment_data) {
        this.apointment_data = apointment_data;
    }

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
