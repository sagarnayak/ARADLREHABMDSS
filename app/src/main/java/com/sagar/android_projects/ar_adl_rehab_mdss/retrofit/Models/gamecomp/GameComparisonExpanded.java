package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp;

import com.google.gson.annotations.SerializedName;


public class GameComparisonExpanded {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private GameComparisonExpandedData gameComparisonExpandedData;
    @SuppressWarnings("unused")
    public GameComparisonExpanded() {
    }
    @SuppressWarnings("unused")
    public GameComparisonExpanded(String status, String message, GameComparisonExpandedData gameComparisonExpandedData) {
        this.status = status;
        this.message = message;
        this.gameComparisonExpandedData = gameComparisonExpandedData;
    }

    public String getStatus() {
        return status;
    }
    @SuppressWarnings("unused")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GameComparisonExpandedData getGameComparisonExpandedData() {
        return gameComparisonExpandedData;
    }
    @SuppressWarnings("unused")
    public void setGameComparisonExpandedData(GameComparisonExpandedData gameComparisonExpandedData) {
        this.gameComparisonExpandedData = gameComparisonExpandedData;
    }
}
