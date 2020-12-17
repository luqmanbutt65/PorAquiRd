package com.example.realestate.Model.GetUpdateData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class User_Data extends RealmObject  {

    /*"user_data": {
            "id": 11,
            "your_id": "54643464564",
            "rnc": "12355555588",
            "user_id": "72",
            "photo_id": null,
            "phone_number": "07546643555",
            "work_permit": null,
            "company_name": "company",
            "verified": "0",
            "created_at": "2020-12-15 19:16:38",
            "updated_at": "2020-12-15 19:16:38"
        }
     */
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("rnc")
    @Expose
    private String rnc;

    @SerializedName("photo_id")
    @Expose
    private String photo_id;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    @SerializedName("work_permit")
    @Expose
    private String work_permit ;

    @SerializedName("company_name")
    @Expose
    private String company_name;

    @SerializedName("verified")
    @Expose
    private String verified;

    @SerializedName("your_id")
    @Expose
    private String your_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
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

    public String getYour_id() {
        return your_id;
    }

    public void setYour_id(String your_id) {
        this.your_id = your_id;
    }
}
