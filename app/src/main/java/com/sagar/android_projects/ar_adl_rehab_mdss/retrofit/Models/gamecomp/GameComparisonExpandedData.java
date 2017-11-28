package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GameComparisonExpandedData {
    @SerializedName("gameComparison")
    private ArrayList<GameComparison> gameComparisons;

    @SuppressWarnings("unused")
    public GameComparisonExpandedData() {
    }

    @SuppressWarnings("unused")
    public GameComparisonExpandedData(ArrayList<GameComparison> gameComparisons) {
        this.gameComparisons = gameComparisons;
    }

    public ArrayList<GameComparison> getGameComparisons() {
        return gameComparisons;
    }

    @SuppressWarnings("unused")
    public void setGameComparisons(ArrayList<GameComparison> gameComparisons) {
        this.gameComparisons = gameComparisons;
    }
}
