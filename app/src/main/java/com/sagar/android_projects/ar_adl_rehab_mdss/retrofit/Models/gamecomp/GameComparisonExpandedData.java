package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/22/2017.
 * "gameComparison": [
 * {
 * "date": "8/20/2016 (1)",
 * "data": [
 * {
 * "name": "ReachTouch",
 * "reps": "3"
 * },
 * {
 * "name": "GripRelease",
 * "reps": "3"
 * }
 * ]
 * },
 * {
 * "date": "8/20/2016 (2)",
 * "data": [
 * {
 * "name": "ReachTouch",
 * "reps": "3"
 * },
 * {
 * "name": "GripRelease",
 * "reps": "3"
 * }
 * ]
 * }
 * ]
 */
public class GameComparisonExpandedData {
    @SerializedName("gameComparison")
    private ArrayList<GameComparison> gameComparisons;

    public GameComparisonExpandedData() {
    }

    public GameComparisonExpandedData(ArrayList<GameComparison> gameComparisons) {
        this.gameComparisons = gameComparisons;
    }

    public ArrayList<GameComparison> getGameComparisons() {
        return gameComparisons;
    }

    public void setGameComparisons(ArrayList<GameComparison> gameComparisons) {
        this.gameComparisons = gameComparisons;
    }
}
