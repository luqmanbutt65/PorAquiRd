package com.example.realestate.Model.Plans.Purchasedplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PurchasedplanData {

    @SerializedName("purchases")
    @Expose
    private ArrayList<PurchasedPlan> purchasedPlanArrayList;

    public ArrayList<PurchasedPlan> getPurchasedPlanArrayList() {
        return purchasedPlanArrayList;
    }

    public void setPurchasedPlanArrayList(ArrayList<PurchasedPlan> purchasedPlanArrayList) {
        this.purchasedPlanArrayList = purchasedPlanArrayList;
    }
}
