package com.example.realestate.Model.RejectedAppointmentMessages;

import com.example.realestate.Model.Plans.Purchasedplan.PurchasedPlan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RejectMessagesData {

    @SerializedName("messages")
    @Expose
    private ArrayList<RejectedMessages> rejectedMessagesArrayList;

    public ArrayList<RejectedMessages> getRejectedMessagesArrayList() {
        return rejectedMessagesArrayList;
    }

    public void setRejectedMessagesArrayList(ArrayList<RejectedMessages> rejectedMessagesArrayList) {
        this.rejectedMessagesArrayList = rejectedMessagesArrayList;
    }
}
