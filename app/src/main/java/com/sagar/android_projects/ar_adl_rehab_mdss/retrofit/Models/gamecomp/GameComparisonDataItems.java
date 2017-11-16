package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */
public class GameComparisonDataItems {
    @SerializedName("name")
    private String name;
    @SerializedName("reps")
    private String reps;

    public GameComparisonDataItems() {
    }

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

    public void setReps(String reps) {
        this.reps = reps;
    }
}
