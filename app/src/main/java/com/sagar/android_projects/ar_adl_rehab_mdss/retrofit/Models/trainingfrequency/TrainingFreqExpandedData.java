package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/22/2017.
 * "trainingFrequeny": [
 * {
 * "date": "8/20/2016",
 * "reps": "2"
 * }
 * ]
 */
public class TrainingFreqExpandedData {
    @SerializedName("trainingFrequeny")
    private ArrayList<TrainingFrequency> trainingFrequencies;

    public TrainingFreqExpandedData() {
    }

    public TrainingFreqExpandedData(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }

    public ArrayList<TrainingFrequency> getTrainingFrequencies() {
        return trainingFrequencies;
    }

    public void setTrainingFrequencies(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }
}
