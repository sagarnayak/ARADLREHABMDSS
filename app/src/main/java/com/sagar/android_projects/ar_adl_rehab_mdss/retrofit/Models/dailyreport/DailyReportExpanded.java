package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport;

import com.google.gson.annotations.SerializedName;


public class DailyReportExpanded {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataOfDailyReportExpanded dailyReportDateAndScores;

    @SuppressWarnings("unused")
    public DailyReportExpanded() {
    }

    @SuppressWarnings("unused")
    public DailyReportExpanded(String status, String message, DataOfDailyReportExpanded dailyReportDateAndScores) {
        this.status = status;
        this.message = message;
        this.dailyReportDateAndScores = dailyReportDateAndScores;
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

    public DataOfDailyReportExpanded getDailyReportDateAndScores() {
        return dailyReportDateAndScores;
    }

    @SuppressWarnings("unused")
    public void setDailyReportDateAndScores(DataOfDailyReportExpanded dailyReportDateAndScores) {
        this.dailyReportDateAndScores = dailyReportDateAndScores;
    }
}
