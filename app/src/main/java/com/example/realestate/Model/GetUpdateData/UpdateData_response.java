package com.example.realestate.Model.GetUpdateData;

import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.PropertiesExtra;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpdateData_response {





    /*

    {
    "status": "200",
    "message": "user whole detail",
    "data": {

    }
}
  */
    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private UpdateData_data data;


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

    public UpdateData_data getData() {
        return data;
    }

    public void setData(UpdateData_data data) {
        this.data = data;
    }
}
