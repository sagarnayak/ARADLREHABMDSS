package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GameComparison {
    @SerializedName("date")
    private String date;
    @SerializedName("data")
    private ArrayList<GameComparisonDataItems> gameComparisonDataItems;

    @SuppressWarnings("unused")
    public GameComparison() {
    }

    @SuppressWarnings("unused")
    public GameComparison(String date, ArrayList<GameComparisonDataItems> gameComparisonDataItems) {
        this.date = date;
        this.gameComparisonDataItems = gameComparisonDataItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<GameComparisonDataItems> getGameComparisonDataItems() {
        return gameComparisonDataItems;
    }

    @SuppressWarnings("unused")
    public void setGameComparisonDataItems(ArrayList<GameComparisonDataItems> gameComparisonDataItems) {
        this.gameComparisonDataItems = gameComparisonDataItems;
    }
}
