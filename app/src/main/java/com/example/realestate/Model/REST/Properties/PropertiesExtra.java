package com.example.realestate.Model.REST.Properties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertiesExtra {



    /*
         "id": 8,
          "property_id": "3",
          "bathrooms": "4",
          "bedrooms": "4",
          "patio": "0",
          "parking": "1",
          "pets": "0",
          "created_at": "2020-12-04 12:03:02",
          "updated_at": "2020-12-04 12:03:02"*/



    @SerializedName("property_id")
    @Expose
    private String property_id;

    @SerializedName("bathrooms")
    @Expose
    private String bathrooms;

    @SerializedName("bedrooms")
    @Expose
    private String bedrooms;

    @SerializedName("patio")
    @Expose
    private String Ratio;

    @SerializedName("parking")
    @Expose
    private String parking;

    @SerializedName("pets")
    @Expose
    private String pets;

    public PropertiesExtra(String property_id, String bathrooms, String bedrooms, String ratio, String parking, String pets) {
        this.property_id = property_id;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        Ratio = ratio;
        this.parking = parking;
        this.pets = pets;
    }


    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getRatio() {
        return Ratio;
    }

    public void setRatio(String ratio) {
        Ratio = ratio;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getPets() {
        return pets;
    }

    public void setPets(String pets) {
        this.pets = pets;
    }
}
