package com.example.realestate.Model.Rating.GetRating;

import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.UserInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User_Reviews {

    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("comments")
    @Expose
    private String comments;

    private  String user_id;
    @SerializedName("user_detail")
    @Expose
    private UserInfo user_detail;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public UserInfo getUser_detail() {
        return user_detail;
    }

    public void setUser_detail(UserInfo user_detail) {
        this.user_detail = user_detail;
    }




}
