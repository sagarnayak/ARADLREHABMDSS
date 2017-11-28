package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency;

import com.google.gson.annotations.SerializedName;


public class TrainingFreqExpanded {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private TrainingFreqExpandedData trainingFreqExpandedData;

    @SuppressWarnings("unused")
    public TrainingFreqExpanded() {
    }

    @SuppressWarnings("unused")
    public TrainingFreqExpanded(String status, String message, TrainingFreqExpandedData trainingFreqExpandedData) {
        this.status = status;
        this.message = message;
        this.trainingFreqExpandedData = trainingFreqExpandedData;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings("unused")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TrainingFreqExpandedData getTrainingFreqExpandedData() {
        return trainingFreqExpandedData;
    }

    @SuppressWarnings("unused")
    public void setTrainingFreqExpandedData(TrainingFreqExpandedData trainingFreqExpandedData) {
        this.trainingFreqExpandedData = trainingFreqExpandedData;
    }
}
