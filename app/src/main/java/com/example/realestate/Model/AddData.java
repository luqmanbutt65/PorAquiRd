package com.example.realestate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddData {



    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("bedrooms")
    @Expose
    private String bedrooms;

    @SerializedName("baths")
    @Expose
    private String baths;

    @SerializedName("area")
    @Expose
    private String area;


    @SerializedName("dateOfConstruction")
    @Expose
    private String dateOfConstruction;

    @SerializedName("petroom")
    @Expose
    private String petroom;


    @SerializedName("parkindlot")
    @Expose
    private String parkinglot;

}


