package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/22/2017.
 * {
 * "status": "success",
 * "message": "Packages Found",
 * "data": [
 * {
 * "gameId": "1",
 * "gameName": "ReachTouch",
 * "customTime": "50.0",
 * "customCount": "3",
 * "nextGameId": "2"
 * },
 * {
 * "gameId": "2",
 * "gameName": "GripRelease",
 * "customTime": "50.0",
 * "customCount": "3",
 * "nextGameId": "2"
 * }
 * ]
 * }
 */
public class UserDetails {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<UserDetailData> userDetailData;

    public UserDetails() {
    }

    public UserDetails(String status, String message, ArrayList<UserDetailData> userDetailData) {
        this.status = status;
        this.message = message;
        this.userDetailData = userDetailData;
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

    public ArrayList<UserDetailData> getUserDetailData() {
        return userDetailData;
    }

    public void setUserDetailData(ArrayList<UserDetailData> userDetailData) {
        this.userDetailData = userDetailData;
    }
}
