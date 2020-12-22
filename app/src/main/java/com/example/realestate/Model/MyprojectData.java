package com.example.realestate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MyprojectData {
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

    public MyprojectData(String city, String location, double rating, double price, String title, int img) {
        this.city = city;
        this.location = location;
        this.rating = rating;
        this.price = price;
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
