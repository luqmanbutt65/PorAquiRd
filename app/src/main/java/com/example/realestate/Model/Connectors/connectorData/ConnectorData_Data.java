package com.example.realestate.Model.Connectors.connectorData;

import com.example.realestate.Model.Connectors.Connectors;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ConnectorData_Data {
    @SerializedName("connector")
    @Expose
    private Connectors connectors;

    @SerializedName("connector_properties")
    @Expose
    private ArrayList<Connector_Properties> connector_propertiesArrayList;

    @SerializedName("user_reviews")
    @Expose
    private ArrayList<Connector_reviews> connector_reviewsArrayList;

    public Connectors getConnectors() {
        return connectors;
    }

    public void setConnectors(Connectors connectors) {
        this.connectors = connectors;
    }

    public ArrayList<Connector_Properties> getConnector_propertiesArrayList() {
        return connector_propertiesArrayList;
    }

    public void setConnector_propertiesArrayList(ArrayList<Connector_Properties> connector_propertiesArrayList) {
        this.connector_propertiesArrayList = connector_propertiesArrayList;
    }

    public ArrayList<Connector_reviews> getConnector_reviewsArrayList() {
        return connector_reviewsArrayList;
    }

    public void setConnector_reviewsArrayList(ArrayList<Connector_reviews> connector_reviewsArrayList) {
        this.connector_reviewsArrayList = connector_reviewsArrayList;
    }
}

