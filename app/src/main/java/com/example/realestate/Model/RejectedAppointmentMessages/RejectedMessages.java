package com.example.realestate.Model.RejectedAppointmentMessages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectedMessages {


  /*   "id": 28,
                "user_id": "88",
                "property_id": "74",
                "property_user": "1",
                "time": "2021-01-12 13:29:35",
                "status": "reject",
                "message": "sdfsdf",
                "created_at": "2021-01-05 15:59:47",
                "updated_at": "2021-01-05 15:59:47",
                "property_owner": "admin"
            */


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("property_id")
    @Expose
    private String property_id;
    @SerializedName("property_user")
    @Expose
    private String property_user;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("property_owner")
    @Expose
    private String property_owner;


    @SerializedName("property_details")
    @Expose
    private PropertyDetail propertyDetail;


    public String getProperty_owner() {
        return property_owner;
    }

    public void setProperty_owner(String property_owner) {
        this.property_owner = property_owner;
    }

    public PropertyDetail getPropertyDetail() {
        return propertyDetail;
    }

    public void setPropertyDetail(PropertyDetail propertyDetail) {
        this.propertyDetail = propertyDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getProperty_user() {
        return property_user;
    }

    public void setProperty_user(String property_user) {
        this.property_user = property_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
