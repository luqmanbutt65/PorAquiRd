package com.example.realestate.Model.Connectors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Connectors {



    /*
    *  "id": 88,
                "your_id": "12312312312",
                "rnc": null,
                "user_id": "88",
                "photo_id": "muaaz_new_full.jpg-1609865908.jpeg",
                "phone_number": "03224638420",
                "work_permit": "f.txt-1609865908.txt",
                "company_name": "StepInn Solutions",
                "verified": "1",
                "rating": "0",
                "created_at": "2020-12-07 13:13:21",
                "updated_at": "2020-12-07 13:13:21",
                "name": "Sayyed Muaaz",
                "user_name": null,
                "email": "sayyedmuaaz@gmail.com",
                "password": "$2y$10$mdqGZDo1MwqG3NduCmfHlObQfs0jD.RM8/KL4xTwnkeAQpfEBfX.C",
                "number": "03224638420",
                "address": "House# 29, Street# 53, Nusrat Street, Dharampura",
                "city": "Lahore",
                "sector": null,
                "user_role": "user",
                "user_image": "hhhy.jpg-1609865753.jpeg",
                "remember_token": "dA1MWjknleNzOj93Fyh0deaczNQ9f7s3y6pRaffaIg5mhx5WkFAz1SLObKK8",
                "secret_key": null,
                "verification_code": null,
                "otp_verify": 0,
                "device_token": "e2xfmnkrqOH_kOZ2Imjs9T:APA91bFD1gg106wZ2YKICNPIDjTk9jj6H8E1hhRa1jJp1rlXSmvyH11rh4L5BIKY_XzlepBTH0LWMEyNNurdTIS5PPvPeUmTfr8tDW3TS_5K9FbFYbizVljWJj9b-2qcASPPOzgUt0jO"*/


    @SerializedName("connectorId")
    @Expose
    private String connectorId;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("your_id")
    @Expose
    private String your_id;

    @SerializedName("rnc")
    @Expose
    private String rnc;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("photo_id")
    @Expose
    private String photo_id;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    @SerializedName("work_permit")
    @Expose
    private String work_permit;

    @SerializedName("company_name")
    @Expose
    private String company_name;

    @SerializedName("verified")
    @Expose
    private String verified;

    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("city")
    @Expose
    private String city;


    @SerializedName("sector")
    @Expose
    private String sector;

    @SerializedName("user_role")
    @Expose
    private String user_role;

    @SerializedName("user_image")
    @Expose
    private String user_image;

    @SerializedName("remember_token")
    @Expose
    private String remember_token;

    @SerializedName("secret_key")
    @Expose
    private String secret_key;

    @SerializedName("verification_code")
    @Expose
    private String verification_code;

    @SerializedName("otp_verify")
    @Expose
    private String otp_verify;

    @SerializedName("device_token")
    @Expose
    private String device_token;
    @SerializedName("created_at")
    @Expose
    private String created_at;

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYour_id() {
        return your_id;
    }

    public void setYour_id(String your_id) {
        this.your_id = your_id;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getWork_permit() {
        return work_permit;
    }

    public void setWork_permit(String work_permit) {
        this.work_permit = work_permit;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getRating() {
        if (rating == null) {
            rating = "";
        }
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        if (number == null) {
            number = "";
        }
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {

        if (city == null) {
            number = "";
        }
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_image() {

        if (user_image == null) {
            user_image = "";
        }
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getOtp_verify() {
        return otp_verify;
    }

    public void setOtp_verify(String otp_verify) {
        this.otp_verify = otp_verify;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}
