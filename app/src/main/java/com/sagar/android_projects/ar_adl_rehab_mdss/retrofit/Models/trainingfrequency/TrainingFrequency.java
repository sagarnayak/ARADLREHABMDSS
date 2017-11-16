package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */

public class TrainingFrequency {
    @SerializedName("date")
    private String date;
    @SerializedName("reps")
    private String reps;

    public TrainingFrequency() {
    }

    public TrainingFrequency(String date, String reps) {
        this.date = date;
        this.reps = reps;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }
}
