package com.example.realestate.Model.Connectors.connectorData;

import com.example.realestate.Model.Connectors.Connector_Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConnectorData_response {

    /*
    *  "review_count": 3,
        "comment_count": 3*/

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ConnectorData_Data data;

    @SerializedName("review_count")
    @Expose
    private int review_count;

    @SerializedName("comment_count")
    @Expose
    private int comment_count;


    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
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

    public ConnectorData_Data getData() {
        return data;
    }

    public void setData(ConnectorData_Data data) {
        this.data = data;
    }
}
