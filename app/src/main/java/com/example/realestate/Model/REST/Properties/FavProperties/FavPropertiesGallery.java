package com.example.realestate.Model.REST.Properties.FavProperties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavPropertiesGallery {
    @SerializedName("property_images")
    @Expose
    private String property_images;

    @SerializedName("property_id")
    @Expose
    private int property_id;

    int ttype=0;

    public int getTtype() {
        return ttype;
    }

    public void setTtype(int ttype) {
        this.ttype = ttype;
    }

    public String getProperty_images() {
        return property_images;
    }

    public void setProperty_images(String property_images) {
        this.property_images = property_images;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }
}
