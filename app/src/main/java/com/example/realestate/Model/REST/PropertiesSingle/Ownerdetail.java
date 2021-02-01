package com.example.realestate.Model.REST.PropertiesSingle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ownerdetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_image")
    @Expose
    private String user_image;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("sector")
    @Expose
    private String sector;

    @SerializedName("your_id")
    @Expose
    private String your_id;

    @SerializedName("rnc")
    @Expose
    private String rnc;

    @SerializedName("phone_number")
    @Expose
    private String cell_number;

    @SerializedName("company_name")
    @Expose
    private String company_name;


    @SerializedName("upload_limit")
    @Expose
    private String upload_limit;

    @SerializedName("expiry_date")
    @Expose
    private String expiry_date;


    @SerializedName("plan_buy_date")
    @Expose
    private String plan_buy_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
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

    public String getCell_number() {
        return cell_number;
    }

    public void setCell_number(String cell_number) {
        this.cell_number = cell_number;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getUpload_limit() {
        return upload_limit;
    }

    public void setUpload_limit(String upload_limit) {
        this.upload_limit = upload_limit;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getPlan_buy_date() {
        return plan_buy_date;
    }

    public void setPlan_buy_date(String plan_buy_date) {
        this.plan_buy_date = plan_buy_date;
    }
}