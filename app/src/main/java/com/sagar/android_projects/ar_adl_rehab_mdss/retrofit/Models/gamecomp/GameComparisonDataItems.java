package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp;

import com.google.gson.annotations.SerializedName;


public class GameComparisonDataItems {
    @SerializedName("name")
    private String name;
    @SerializedName("reps")
    private String reps;

    @SuppressWarnings("unused")
    public GameComparisonDataItems() {
    }

    @SuppressWarnings("unused")
    public GameComparisonDataItems(String name, String reps) {
        this.name = name;
        this.reps = reps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReps() {
        return reps;
    }

    @SuppressWarnings("unused")
    public void setReps(String reps) {
        this.reps = reps;
    }
}
