package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.usergame;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */

public class UserGameResponse {
    @SerializedName("result")
    private String result;

    public UserGameResponse() {
    }

    public UserGameResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
