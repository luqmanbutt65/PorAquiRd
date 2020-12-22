package com.example.realestate.Model.GetUpdateData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class User extends RealmObject {
    /*
     "user": {
                "id": 72,
                "name": "luqman",
                "user_name": null,
                "email": "Luqmanbutt65@gmail.com",
                "password": "$2y$10$T0eKB12YvsfOgbbliEWgpeU/mXNgyjvOeuV8lATKmFjTgU4zVFSXu",
                "number": null,
                "address": "address",
                "city": "sity",
                "sector": "sector",
                "user_role": "user",
                "user_image": "pic.jpg-1607513609.jpeg",
                "verified": "1",
                "remember_token": "UGBwtrcHVIJ8vFZTmDbO4H0DtGW8XqRTdIbET7EafRpuDReKAV0AmNHM8yMp",
                "secret_key": null,
                "verification_code": null,
                "created_at": "2020-12-02 10:40:25",
                "updated_at": "2020-12-02 10:40:25"
            }
    */
    @SerializedName("id")
    @Expose
    private Integer id;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


}
