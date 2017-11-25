package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by SAGAR on 11/25/2017.
 */
public class EditUser {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("data")
    private ArrayList<UserDetailData> userDetailData;

    public EditUser() {
    }

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

    public ArrayList<UserDetailData> getUserDetailData() {
        return userDetailData;
    }

    public void setUserDetailData(ArrayList<UserDetailData> userDetailData) {
        this.userDetailData = userDetailData;
    }
}
