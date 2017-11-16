package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/16/2017.
 */

public class TrainingFrequencyList {
    @SerializedName("data")
    private ArrayList<TrainingFrequency> trainingFrequencies;

    public TrainingFrequencyList() {
    }

    public TrainingFrequencyList(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }

    public ArrayList<TrainingFrequency> getTrainingFrequencies() {
        return trainingFrequencies;
    }

    public void setTrainingFrequencies(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }
}
