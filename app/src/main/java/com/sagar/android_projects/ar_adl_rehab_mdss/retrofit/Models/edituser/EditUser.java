package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class EditUser {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("data")
    private ArrayList<UserDetailData> userDetailData;

    public EditUser() {
    }

    @SuppressWarnings("unused")
    public EditUser(String userId, ArrayList<UserDetailData> userDetailData) {
        this.userId = userId;
        this.userDetailData = userDetailData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @SuppressWarnings("unused")
    public ArrayList<UserDetailData> getUserDetailData() {
        return userDetailData;
    }

    public void setUserDetailData(ArrayList<UserDetailData> userDetailData) {
        this.userDetailData = userDetailData;
    }
}
