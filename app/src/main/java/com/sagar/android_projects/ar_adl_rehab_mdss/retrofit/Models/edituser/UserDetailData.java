package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/22/2017.
 * {
 * "gameId": "1",
 * "gameName": "ReachTouch",
 * "customTime": "50.0",
 * "customCount": "3",
 * "nextGameId": "2"
 * }
 */
public class UserDetailData {
    @SerializedName("gameId")
    private String gameId;
    @SerializedName("gameName")
    private String gameName;
    @SerializedName("customTime")
    private String customTime;
    @SerializedName("customCount")
    private String customCount;
    @SerializedName("nextGameId")
    private String nextGameId;

    public UserDetailData() {
    }

    public UserDetailData(String gameId, String gameName, String customTime, String customCount, String nextGameId) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.customTime = customTime;
        this.customCount = customCount;
        this.nextGameId = nextGameId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getCustomTime() {
        return customTime;
    }

    public void setCustomTime(String customTime) {
        this.customTime = customTime;
    }

    public String getCustomCount() {
        return customCount;
    }

    public void setCustomCount(String customCount) {
        this.customCount = customCount;
    }

    public String getNextGameId() {
        return nextGameId;
    }

    public void setNextGameId(String nextGameId) {
        this.nextGameId = nextGameId;
    }
}
