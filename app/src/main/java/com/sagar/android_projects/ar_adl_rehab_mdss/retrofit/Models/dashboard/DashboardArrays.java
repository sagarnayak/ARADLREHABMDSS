package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard;

import com.google.gson.annotations.SerializedName;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport.DailyReport;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparison;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetation;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency.TrainingFrequency;

import java.util.ArrayList;


public class DashboardArrays {
    @SerializedName("dailyReport")
    private ArrayList<DailyReport> dailyReports;
    @SerializedName("trainingFrequeny")
    private ArrayList<TrainingFrequency> trainingFrequencies;
    @SerializedName("gameComparison")
    private ArrayList<GameComparison> gameComparisons;
    @SerializedName("gameRepetation")
    private ArrayList<GameRepetation> gameRepetations;

    @SuppressWarnings("unused")
    public DashboardArrays() {
    }

    @SuppressWarnings("unused")
    public DashboardArrays(ArrayList<DailyReport> dailyReports,
                           ArrayList<TrainingFrequency> trainingFrequencies,
                           ArrayList<GameComparison> gameComparisons,
                           ArrayList<GameRepetation> gameRepetations) {
        this.dailyReports = dailyReports;
        this.trainingFrequencies = trainingFrequencies;
        this.gameComparisons = gameComparisons;
        this.gameRepetations = gameRepetations;
    }

    public ArrayList<DailyReport> getDailyReports() {
        return dailyReports;
    }

    @SuppressWarnings("unused")
    public void setDailyReports(ArrayList<DailyReport> dailyReports) {
        this.dailyReports = dailyReports;
    }

    public ArrayList<TrainingFrequency> getTrainingFrequencies() {
        return trainingFrequencies;
    }

    @SuppressWarnings("unused")
    public void setTrainingFrequencies(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }

    public ArrayList<GameComparison> getGameComparisons() {
        return gameComparisons;
    }

    @SuppressWarnings("unused")
    public void setGameComparisons(ArrayList<GameComparison> gameComparisons) {
        this.gameComparisons = gameComparisons;
    }

    public ArrayList<GameRepetation> getGameRepetations() {
        return gameRepetations;
    }

    @SuppressWarnings("unused")
    public void setGameRepetations(ArrayList<GameRepetation> gameRepetations) {
        this.gameRepetations = gameRepetations;
    }
}
