package com.example.realestate.Model.GetList.GetListPropertyType;

import com.example.realestate.Model.GetList.Cities_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetpropertyListResponse {


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("messaged")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private PropertyType_Data data;

    public PropertyType_Data getData() {
        return data;
    }

    public void setData(PropertyType_Data data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
