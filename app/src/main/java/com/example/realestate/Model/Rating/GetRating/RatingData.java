package com.example.realestate.Model.Rating.GetRating;

import com.example.realestate.Model.REST.Properties.Properties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RatingData {

/*

"property": {

        },
        "user_reviews": [

        ]
**/
    @SerializedName("property")
    @Expose
    private Properties properties;
    @SerializedName("user_reviews")
    @Expose
    private ArrayList<User_Reviews> user_reviewsArrayList;
    public Properties getProperties() {
        return properties;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    public ArrayList<User_Reviews> getUser_reviewsArrayList() {
        return user_reviewsArrayList;
    }
    public void setUser_reviewsArrayList(ArrayList<User_Reviews> user_reviewsArrayList) {
        this.user_reviewsArrayList = user_reviewsArrayList;
    }
}
