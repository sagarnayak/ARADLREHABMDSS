package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard;

import com.google.gson.annotations.SerializedName;


public class DashboardData {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DashboardArrays data;

    @SuppressWarnings("unused")
    public DashboardData() {
    }

    @SuppressWarnings("unused")
    public DashboardData(String status, String message, DashboardArrays data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public DashboardArrays getData() {
        return data;
    }

    public void setData(DashboardArrays data) {
        this.data = data;
    }
}
