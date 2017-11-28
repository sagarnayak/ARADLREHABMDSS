package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport;

import com.google.gson.annotations.SerializedName;

public class DailyReportDateAndScore {
    @SerializedName("date")
    private String date;
    @SerializedName("score")
    private String score;

    @SuppressWarnings("unused")
    public DailyReportDateAndScore() {
    }

    @SuppressWarnings("unused")
    public DailyReportDateAndScore(String date, String score) {
        this.date = date;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    @SuppressWarnings("unused")
    public void setScore(String score) {
        this.score = score;
    }
}
