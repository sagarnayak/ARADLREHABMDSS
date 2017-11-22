package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/22/2017.
 * {
 * "status": "success",
 * "message": "Packages Found",
 * "data": {
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
 * }
 * }
 */
public class GameComparisonExpanded {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private GameComparisonExpandedData gameComparisonExpandedData;

    public GameComparisonExpanded() {
    }

    public GameComparisonExpanded(String status, String message, GameComparisonExpandedData gameComparisonExpandedData) {
        this.status = status;
        this.message = message;
        this.gameComparisonExpandedData = gameComparisonExpandedData;
    }

    public String getStatus() {
        return status;
    }

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

    public void setGameComparisonExpandedData(GameComparisonExpandedData gameComparisonExpandedData) {
        this.gameComparisonExpandedData = gameComparisonExpandedData;
    }
}
