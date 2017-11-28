package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserDetails {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<UserDetailData> userDetailData;

    @SuppressWarnings("unused")
    public UserDetails() {
    }

    @SuppressWarnings("unused")
    public UserDetails(String status, String message, ArrayList<UserDetailData> userDetailData) {
        this.status = status;
        this.message = message;
        this.userDetailData = userDetailData;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings("unused")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<UserDetailData> getUserDetailData() {
        return userDetailData;
    }

    @SuppressWarnings("unused")
    public void setUserDetailData(ArrayList<UserDetailData> userDetailData) {
        this.userDetailData = userDetailData;
    }
}
