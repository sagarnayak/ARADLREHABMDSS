package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.usergame;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */

public class UserGame {
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

    public UserGame() {
    }

    public UserGame(String gameId, String gameName, String customTime, String customCount, String nextGameId) {
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
