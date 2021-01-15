package com.example.realestate.Model.RejectedAppointmentMessages;

import com.example.realestate.Model.Plans.Purchasedplan.PurchasedplanData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectedMessagesResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private RejectMessagesData data;

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

    public RejectMessagesData getData() {
        return data;
    }

    public void setData(RejectMessagesData data) {
        this.data = data;
    }
}
