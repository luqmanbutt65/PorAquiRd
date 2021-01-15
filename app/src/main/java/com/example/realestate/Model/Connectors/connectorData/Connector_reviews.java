package com.example.realestate.Model.Connectors.connectorData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Connector_reviews {

    /*
       *   "user_image": "none.jpg",
                   "name": "test user",
                   "rating": "4",
                   "comments": "latest",
                   "created_at": "2021-01-06 18:19:24"*/

    @SerializedName("user_image")
    @Expose
    private String user_image;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("comments")
    @Expose
    private String comments;

    @SerializedName("created_at")
    @Expose
    private String time;

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        if (rating == null) {
            rating = "0";
        }
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
