package com.example.realestate.Model.Apoinment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Apointment_Data {


    @SerializedName("appointments")
    @Expose
    private ArrayList<Apointments> apointmentsArrayList;

    public ArrayList<Apointments> getApointmentsArrayList() {
        return apointmentsArrayList;
    }

    public void setApointmentsArrayList(ArrayList<Apointments> apointmentsArrayList) {
        this.apointmentsArrayList = apointmentsArrayList;
    }
}
