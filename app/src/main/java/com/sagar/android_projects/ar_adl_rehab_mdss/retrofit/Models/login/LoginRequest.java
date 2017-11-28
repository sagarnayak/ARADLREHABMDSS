package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login;

import com.google.gson.annotations.SerializedName;


public class LoginRequest {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    @SuppressWarnings("unused")
    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SuppressWarnings("unused")
    public String getUsername() {
        return username;
    }

    @SuppressWarnings("unused")
    public void setUsername(String username) {
        this.username = username;
    }

    @SuppressWarnings("unused")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }
}
