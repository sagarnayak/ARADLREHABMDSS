package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/16/2017.
 */

public class GameRepetation {
    @SerializedName("level")
    private String level;
    @SerializedName("gameName")
    private String gameName;
    @SerializedName("gameId")
    private String gameId;
    @SerializedName("data")
    private ArrayList<GameRepetationDataItem> gameRepetationDataItems;

    public GameRepetation() {
    }

    public GameRepetation(String level, String gameName, String gameId, ArrayList<GameRepetationDataItem> gameRepetationDataItems) {
        this.level = level;
        this.gameName = gameName;
        this.gameId = gameId;
        this.gameRepetationDataItems = gameRepetationDataItems;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public ArrayList<GameRepetationDataItem> getGameRepetationDataItems() {
        return gameRepetationDataItems;
    }

    public void setGameRepetationDataItems(ArrayList<GameRepetationDataItem> gameRepetationDataItems) {
        this.gameRepetationDataItems = gameRepetationDataItems;
    }
}