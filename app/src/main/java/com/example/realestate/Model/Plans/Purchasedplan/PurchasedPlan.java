package com.example.realestate.Model.Plans.Purchasedplan;

import com.example.realestate.Model.Plans.PlanFeatures;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PurchasedPlan {


  /*  "id": 13,
                "user_id": "88",
                "transaction_id": "PAYID-L763MFQ9DE29223W7821410S",
                "plan_id": "9",
                "price": "15.0",
                "status": "0",
                "created_at": "2021-01-12 19:45:49",
                "updated_at": "2021-01-12 19:45:49"
            */


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("plan_id")
    @Expose
    private String plan_id;
    @SerializedName("transaction_id")
    @Expose
    private String transaction_id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
