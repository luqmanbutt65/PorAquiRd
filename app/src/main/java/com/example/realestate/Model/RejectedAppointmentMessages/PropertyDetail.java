package com.example.realestate.Model.RejectedAppointmentMessages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyDetail {


  /*  "main_image": "hacerse-una-casa.jpeg-1608398939.jpeg",
                    "title": "Aparta Estudio"
            */


    @SerializedName("main_image")
    @Expose
    private String main_image;
    @SerializedName("title")
    @Expose
    private String title;

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
