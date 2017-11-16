package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */

public class GameRepetationDataItem {
    @SerializedName("score")
    private String score;
    @SerializedName("rep")
    private String rep;

    public GameRepetationDataItem() {
    }

    public GameRepetationDataItem(String score, String rep) {
        this.score = score;
        this.rep = rep;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }
}
