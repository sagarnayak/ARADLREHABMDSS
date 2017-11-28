package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps;

import com.google.gson.annotations.SerializedName;


public class GameRepetationDataItem {
    @SerializedName("score")
    private String score;
    @SerializedName("rep")
    private String rep;

    @SuppressWarnings("unused")
    public GameRepetationDataItem() {
    }

    @SuppressWarnings("unused")
    public GameRepetationDataItem(String score, String rep) {
        this.score = score;
        this.rep = rep;
    }

    public String getScore() {
        return score;
    }

    @SuppressWarnings("unused")
    public void setScore(String score) {
        this.score = score;
    }

    public String getRep() {
        return rep;
    }

    @SuppressWarnings("unused")
    public void setRep(String rep) {
        this.rep = rep;
    }
}
