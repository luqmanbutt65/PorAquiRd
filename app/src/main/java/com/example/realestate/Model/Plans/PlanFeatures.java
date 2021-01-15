package com.example.realestate.Model.Plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanFeatures {


    /*
    *
    * "id": 9,
                    "title": "prueba",
                    "created_at": "2020-12-04 22:26:58",
                    "updated_at": "2020-12-04 22:26:58"
                    * */

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
