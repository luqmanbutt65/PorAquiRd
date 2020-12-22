package com.example.realestate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {


    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("Image")
    @Expose
    private int img;

    public Features(String description, int img) {
        this.description = description;
        this.img = img;

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
