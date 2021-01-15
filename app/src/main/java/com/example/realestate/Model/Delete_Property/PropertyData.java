package com.example.realestate.Model.Delete_Property;

import com.example.realestate.Model.REST.Properties.Properties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyData {
    @SerializedName("property")
    @Expose
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
