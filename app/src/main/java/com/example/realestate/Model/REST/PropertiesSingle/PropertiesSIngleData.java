package com.example.realestate.Model.REST.PropertiesSingle;

import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.PropertiesExtra;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PropertiesSIngleData {
    @SerializedName("property")
    @Expose
    private Properties singleProperty;


    @SerializedName("extra")
    @Expose
    private PropertiesExtra propertiesExtra;

    @SerializedName("gallery")
    @Expose
    private ArrayList<PropertiesGallery> propertiesGalleryArrayList;

    public Properties getSingleProperty() {
        return singleProperty;
    }

    public void setSingleProperty(Properties singleProperty) {
        this.singleProperty = singleProperty;
    }
}
