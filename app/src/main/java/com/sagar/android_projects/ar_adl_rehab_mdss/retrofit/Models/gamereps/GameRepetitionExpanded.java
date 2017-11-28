package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GameRepetitionExpanded {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<GameRepetation> gameRepetations;

    @SuppressWarnings("unused")
    public GameRepetitionExpanded() {
    }

    @SuppressWarnings("unused")
    public GameRepetitionExpanded(String status,
                                  String message,
                                  ArrayList<GameRepetation> gameRepetations) {
        this.status = status;
        this.message = message;
        this.gameRepetations = gameRepetations;
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

    public ArrayList<GameRepetation> getGameRepetations() {
        return gameRepetations;
    }

    @SuppressWarnings("unused")
    public void setGameRepetations(ArrayList<GameRepetation> gameRepetations) {
        this.gameRepetations = gameRepetations;
    }
}
