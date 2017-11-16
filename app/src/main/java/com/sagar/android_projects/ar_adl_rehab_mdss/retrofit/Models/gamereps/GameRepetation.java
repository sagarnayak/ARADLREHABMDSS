package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/16/2017.
 */

public class GameRepetation {
    @SerializedName("level")
    private String level;
    @SerializedName("data")
    private ArrayList<GameRepetationDataItem> gameRepetationDataItems;

    public GameRepetation() {
    }

    public GameRepetation(String level, ArrayList<GameRepetationDataItem> gameRepetationDataItems) {
        this.level = level;
        this.gameRepetationDataItems = gameRepetationDataItems;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ArrayList<GameRepetationDataItem> getGameRepetationDataItems() {
        return gameRepetationDataItems;
    }

    public void setGameRepetationDataItems(ArrayList<GameRepetationDataItem> gameRepetationDataItems) {
        this.gameRepetationDataItems = gameRepetationDataItems;
    }
}
