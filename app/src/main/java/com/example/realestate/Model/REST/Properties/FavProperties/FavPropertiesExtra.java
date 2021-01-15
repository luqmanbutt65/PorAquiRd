package com.example.realestate.Model.REST.Properties.FavProperties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavPropertiesExtra {



    /*
         "type": "parking",
                        "image": "public/assets/img/parking.png",
                        "quantity": "2"*/


    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("quantity")
    @Expose
    private String quantity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
