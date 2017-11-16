package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */

public class LoginRequest {

    @SerializedName("userName")
    private String userName;
    @SerializedName("password")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
