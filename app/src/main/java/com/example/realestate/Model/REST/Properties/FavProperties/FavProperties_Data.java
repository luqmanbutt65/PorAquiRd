package com.example.realestate.Model.REST.Properties.FavProperties;

import com.example.realestate.Model.REST.Properties.Properties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class FavProperties_Data extends RealmObject {
    @SerializedName("properties")
    @Expose
    private RealmList<FavProperties> propertiesArrayList;

    public RealmList<FavProperties> getPropertiesArrayList() {
        return propertiesArrayList;
    }

    public void setPropertiesArrayList(RealmList<FavProperties> propertiesArrayList) {
        this.propertiesArrayList = propertiesArrayList;
    }
}
