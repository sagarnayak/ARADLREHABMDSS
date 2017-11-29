package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp;

import com.google.gson.annotations.SerializedName;


public class GameComparisonDataItems {
    @SerializedName("name")
    private String name;
    @SerializedName("game_score")
    private String gameScore;

    @SuppressWarnings("unused")
    public GameComparisonDataItems() {
    }

    public GameComparisonDataItems(String name, String gameScore) {
        this.name = name;
        this.gameScore = gameScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameScore() {
        return gameScore;
    }

    public void setGameScore(String gameScore) {
        this.gameScore = gameScore;
    }
}
