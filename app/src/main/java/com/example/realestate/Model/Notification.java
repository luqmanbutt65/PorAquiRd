package com.example.realestate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {


    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("description")
    @Expose
    private String Description;
    @SerializedName("time")
    @Expose
    private String time;

    public Notification(String Title, String description, String time) {
        this.Title = Title;
        this.Description = description;
        this.time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


