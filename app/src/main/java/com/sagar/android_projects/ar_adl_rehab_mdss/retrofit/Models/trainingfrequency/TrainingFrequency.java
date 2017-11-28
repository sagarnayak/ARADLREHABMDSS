package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency;

import com.google.gson.annotations.SerializedName;


public class TrainingFrequency {
    @SerializedName("date")
    private String date;
    @SerializedName("reps")
    private String reps;

    @SuppressWarnings("unused")
    public TrainingFrequency() {
    }

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    public void setReps(String reps) {
        this.reps = reps;
    }
}
