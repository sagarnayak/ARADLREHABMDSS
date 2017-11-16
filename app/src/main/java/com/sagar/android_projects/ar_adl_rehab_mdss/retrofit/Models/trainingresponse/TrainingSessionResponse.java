package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingresponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */

public class TrainingSessionResponse {
    @SerializedName("sessionId")
    private String sessionId;

    public TrainingSessionResponse() {
    }

    public TrainingSessionResponse(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
