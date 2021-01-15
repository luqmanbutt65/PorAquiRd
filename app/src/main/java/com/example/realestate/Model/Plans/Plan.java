package com.example.realestate.Model.Plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Plan {


  /*  "id": 9,
            "title": "Gold",
            "price": "15",
            "created_at": "2020-12-01 16:43:10",
            "updated_at": "2020-12-01 16:43:10",
            */


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("features")
    @Expose
    private ArrayList<PlanFeatures> featuresArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public ArrayList<PlanFeatures> getFeaturesArrayList() {
        return featuresArrayList;
    }

    public void setFeaturesArrayList(ArrayList<PlanFeatures> featuresArrayList) {
        this.featuresArrayList = featuresArrayList;
    }
}
