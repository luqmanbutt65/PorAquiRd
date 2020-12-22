package com.example.realestate.Model.Rating.GetRating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating_Response {

/*

{
    "status": "200",
    "message": "single property",
    "data": {

    }
}


*/





    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private RatingData ratingData;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RatingData getRatingData() {
        return ratingData;
    }

    public void setRatingData(RatingData ratingData) {
        this.ratingData = ratingData;
    }



}
