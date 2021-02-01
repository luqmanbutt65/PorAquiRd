package com.example.realestate.Model.Apoinment;

import com.example.realestate.Model.REST.Properties.Properties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apointments {


    /*

                       "id": 3,
                "user_id": "88",
                "property_id": "74",
                "property_user": "1",
                "time": "2020-12-21 19:39:30",
                "status": "pending",
                "created_at": "2020-12-21 18:38:39",
                "updated_at": "2020-12-21 18:38:39"

    * */


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

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("appoint_property")
    @Expose
    private Properties properties;

    @SerializedName("appointment_type")
    @Expose
    private String appointment_type;

    public String getAppointment_type() {
        return appointment_type;
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {

        this.properties = properties;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
