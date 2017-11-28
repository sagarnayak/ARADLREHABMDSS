package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class UserListResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<User> data;

    @SuppressWarnings("unused")
    public UserListResponse() {
    }

    @SuppressWarnings("unused")
    public UserListResponse(String status, String message, ArrayList<User> data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }
}
