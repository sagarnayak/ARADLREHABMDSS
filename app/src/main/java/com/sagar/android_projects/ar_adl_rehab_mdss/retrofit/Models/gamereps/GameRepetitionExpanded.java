package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by SAGAR on 11/24/2017.
 * {
 * "status": "success",
 * "message": "Packages Found",
 * "data": [
 * {
 * "level": "ReachTouch 2016-08-20, 1",
 * "data": [
 * {
 * "score": "0",
 * "rep": "0"
 * },
 * {
 * "score": "0",
 * "rep": "1"
 * },
 * {
 * "score": "0",
 * "rep": "2"
 * }
 * ]
 * },
 * {
 * "level": "ReachTouch 2016-08-20, 2",
 * "data": [
 * {
 * "score": "0",
 * "rep": "0"
 * },
 * {
 * "score": "0",
 * "rep": "1"
 * },
 * {
 * "score": "0",
 * "rep": "2"
 * }
 * ]
 * }
 * ]
 * }
 */

public class GameRepetitionExpanded {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<GameRepetation> gameRepetations;

    public GameRepetitionExpanded() {
    }

    public GameRepetitionExpanded(String status, String message, ArrayList<GameRepetation> gameRepetations) {
        this.status = status;
        this.message = message;
        this.gameRepetations = gameRepetations;
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

    public ArrayList<GameRepetation> getGameRepetations() {
        return gameRepetations;
    }

    public void setGameRepetations(ArrayList<GameRepetation> gameRepetations) {
        this.gameRepetations = gameRepetations;
    }
}
