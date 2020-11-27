package com.example.realestate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppointmentsData {

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("town")
    @Expose
    private String town;

    @SerializedName("dateTime")
    @Expose
    private String dateTime;

    @SerializedName("status")
    @Expose

    private String status;


    @SerializedName("image")
    @Expose
    private int img;


    public AppointmentsData(String description, String town, String dateTime, String status, int img) {
        this.description = description;
        this.town = town;
        this.dateTime = dateTime;
        this.status = status;
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
