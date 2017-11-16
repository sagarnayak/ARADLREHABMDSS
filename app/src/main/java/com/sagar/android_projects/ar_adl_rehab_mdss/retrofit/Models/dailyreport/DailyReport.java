package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/16/2017.
 */
public class DailyReport {
    @SerializedName("name")
    private String name;
    @SerializedName("data")
    private ArrayList<DailyReportDateAndScore> data;

    public DailyReport() {
    }

    public DailyReport(String name, ArrayList<DailyReportDateAndScore> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DailyReportDateAndScore> getData() {
        return data;
    }

    public void setData(ArrayList<DailyReportDateAndScore> data) {
        this.data = data;
    }
}
