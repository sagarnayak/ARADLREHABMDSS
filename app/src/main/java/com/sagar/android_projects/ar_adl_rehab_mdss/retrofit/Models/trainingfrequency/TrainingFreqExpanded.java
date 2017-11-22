package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/22/2017.
 * {
 * "status": "success",
 * "message": "Packages Found",
 * "data": {
 * "trainingFrequeny": [
 * {
 * "date": "8/20/2016",
 * "reps": "2"
 * }
 * ]
 * }
 * }
 */
public class TrainingFreqExpanded {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private TrainingFreqExpandedData trainingFreqExpandedData;

    public TrainingFreqExpanded() {
    }

    public TrainingFreqExpanded(String status, String message, TrainingFreqExpandedData trainingFreqExpandedData) {
        this.status = status;
        this.message = message;
        this.trainingFreqExpandedData = trainingFreqExpandedData;
    }

    public String getStatus() {
        return status;
    }

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

    public void setTrainingFreqExpandedData(TrainingFreqExpandedData trainingFreqExpandedData) {
        this.trainingFreqExpandedData = trainingFreqExpandedData;
    }
}
