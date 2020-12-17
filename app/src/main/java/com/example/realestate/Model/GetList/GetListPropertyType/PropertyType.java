package com.example.realestate.Model.GetList.GetListPropertyType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyType {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("city")
    @Expose
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
