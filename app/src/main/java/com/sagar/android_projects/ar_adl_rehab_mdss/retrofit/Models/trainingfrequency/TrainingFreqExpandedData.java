package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class TrainingFreqExpandedData {
    @SerializedName("trainingFrequeny")
    private ArrayList<TrainingFrequency> trainingFrequencies;

    @SuppressWarnings("unused")
    public TrainingFreqExpandedData() {
    }

    @SuppressWarnings("unused")
    public TrainingFreqExpandedData(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }

    public ArrayList<TrainingFrequency> getTrainingFrequencies() {
        return trainingFrequencies;
    }

    @SuppressWarnings("unused")
    public void setTrainingFrequencies(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }
}
