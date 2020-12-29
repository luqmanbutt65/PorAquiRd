package com.example.realestate.Model.REST.Properties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.realm.RealmObject;

public class Properties {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_id")
    @Expose
    private String use_id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sale_type")
    @Expose
    private String sale_type;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("main_image")
    @Expose
    private String main_image;
    @SerializedName("top_offer")
    @Expose
    private String top_offer;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("bedroom")
    @Expose
    private String bedroom;

    @SerializedName("bath")
    @Expose
    private String bath;
    @SerializedName("property_type")
    @Expose
    private String property_type;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("sector")
    @Expose
    private String sector;
    @SerializedName("unit_of_measure")
    @Expose
    private String unit_of_measure;

    @SerializedName("date_of_construction")
    @Expose
    private String date_of_construction;

    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("ratingg")
    @Expose
    private String user_rating;
    @SerializedName("extra")
    @Expose
    private PropertiesExtra propertiesExtra;

    @SerializedName("gallery")
    @Expose


    private ArrayList<PropertiesGallery> propertiesGalleryArrayList;
    @SerializedName("liked")
    @Expose
    private boolean like;


    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public PropertiesExtra getPropertiesExtra() {
        return propertiesExtra;
    }

    public void setPropertiesExtra(PropertiesExtra propertiesExtra) {
        this.propertiesExtra = propertiesExtra;
    }

    public ArrayList<PropertiesGallery> getPropertiesGalleryArrayList() {
        return propertiesGalleryArrayList;
    }

    public void setPropertiesGalleryArrayList(ArrayList<PropertiesGallery> propertiesGalleryArrayList) {
        this.propertiesGalleryArrayList = propertiesGalleryArrayList;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUnit_of_measure() {
        return unit_of_measure;
    }

    public void setUnit_of_measure(String unit_of_measure) {
        this.unit_of_measure = unit_of_measure;
    }

    public String getDate_of_construction() {
        return date_of_construction;
    }

    public void setDate_of_construction(String date_of_construction) {
        this.date_of_construction = date_of_construction;
    }

    public String getBedroom() {


        //  return bedroom;
        return "2";

    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getBath() {

        //return bath;
        return "4";   //TODO: For Testing before
    }

    public void setBath(String bath) {
        this.bath = bath;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUse_id() {
        return use_id;
    }

    public void setUse_id(String use_id) {
        this.use_id = use_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSale_type() {
        return sale_type;
    }

    public void setSale_type(String sale_type) {
        this.sale_type = sale_type;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMain_image() {
        if (main_image == null) {
            main_image = "hacerse-una-casa.jpeg-1608398939.jpeg";
        }
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getTop_offer() {
        return top_offer;
    }

    public void setTop_offer(String top_offer) {
        this.top_offer = top_offer;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }


}
