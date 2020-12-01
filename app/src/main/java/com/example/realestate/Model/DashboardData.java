package com.example.realestate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DashboardData {
    @SerializedName("City")
    @Expose
    private String city;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("rating")
    @Expose
    private double rating;

    @SerializedName("price")
    @Expose

    private double price;
    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("image")
    @Expose
    private int img;

    @SerializedName("bedroom")
    @Expose
    private String bedroom;

    @SerializedName("bath")
    @Expose
    private String bath;

    @SerializedName("area")
    @Expose
    private String area;

    public DashboardData(String city, String location, double rating, double price, String title, int img, String bedroom, String bath, String area) {
        this.city = city;
        this.location = location;
        this.rating = rating;
        this.price = price;
        this.title = title;
        this.img = img;
        this.bedroom = bedroom;
        this.bath = bath;
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
