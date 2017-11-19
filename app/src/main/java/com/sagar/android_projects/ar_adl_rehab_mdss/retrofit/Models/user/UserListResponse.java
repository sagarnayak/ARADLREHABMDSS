package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/16/2017.
 */

public class UserListResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<User> data;

    public UserListResponse() {
    }

    public UserListResponse(String status, String message, ArrayList<User> data) {
        this.status = status;
        this.message = message;
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

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }
}
