package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/22/2017.
 */
public class DataOfDailyReportExpanded {
    @SerializedName("name")
    private String name;
    @SerializedName("data")
    private ArrayList<DailyReportDateAndScore> dailyReportDateAndScores;

    public DataOfDailyReportExpanded() {
    }

    public DataOfDailyReportExpanded(String name, ArrayList<DailyReportDateAndScore> dailyReportDateAndScores) {
        this.name = name;
        this.dailyReportDateAndScores = dailyReportDateAndScores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DailyReportDateAndScore> getDailyReportDateAndScores() {
        return dailyReportDateAndScores;
    }

    public void setDailyReportDateAndScores(ArrayList<DailyReportDateAndScore> dailyReportDateAndScores) {
        this.dailyReportDateAndScores = dailyReportDateAndScores;
    }
}
