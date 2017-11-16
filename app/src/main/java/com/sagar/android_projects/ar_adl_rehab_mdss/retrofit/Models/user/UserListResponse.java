package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/16/2017.
 */

public class UserListResponse {
    @SerializedName("data")
    private ArrayList<User> data;

    public UserListResponse() {
    }

    public UserListResponse(ArrayList<User> data) {
        this.data = data;
    }

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }
}
