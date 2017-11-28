package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class DailyReport {
    @SerializedName("name")
    private String name;
    @SerializedName("game_id")
    private String gameId;
    @SerializedName("data")
    private ArrayList<DailyReportDateAndScore> data;

    @SuppressWarnings("unused")
    public DailyReport() {
    }

    @SuppressWarnings("unused")
    public DailyReport(String name, String gameId, ArrayList<DailyReportDateAndScore> data) {
        this.name = name;
        this.gameId = gameId;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public ArrayList<DailyReportDateAndScore> getData() {
        return data;
    }

    public void setData(ArrayList<DailyReportDateAndScore> data) {
        this.data = data;
    }
}
