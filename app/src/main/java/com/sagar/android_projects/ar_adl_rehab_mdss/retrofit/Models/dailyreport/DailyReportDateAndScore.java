package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */
public class DailyReportDateAndScore {
    @SerializedName("date")
    private String date;
    @SerializedName("score")
    private String score;

    public DailyReportDateAndScore() {
    }

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

    public void setScore(String score) {
        this.score = score;
    }
}
