package com.example.realestate.Model.REST.Properties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertiesGallery {
    @SerializedName("property_images")
    @Expose
    private String property_images;

    @SerializedName("property_id")
    @Expose
    private int property_id;

    public String getProperty_images() {
        return property_images;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public void setProperty_images(String property_images) {
        this.property_images = property_images;
    }
}