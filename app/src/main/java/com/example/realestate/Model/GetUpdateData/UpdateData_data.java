package com.example.realestate.Model.GetUpdateData;

import com.example.realestate.Model.UserInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateData_data {


    /*

    "data": {
        "user": {

        },
        "user_data": {

        }

}
    */
    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("user_data")
    @Expose
    private User_Data user_data;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User_Data getUser_data() {
        return user_data;
    }

    public void setUser_data(User_Data user_data) {
        this.user_data = user_data;
    }
}
