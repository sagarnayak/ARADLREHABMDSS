package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/22/2017.
 * {
 * "status":"success",
 * "message":"Packages Found",
 * "data":
 * {
 * "name":null,
 * "data":
 * [
 * {
 * "date":"8\/21\/2016(4)",
 * "score":"0.00"
 * },
 * {
 * "date":"8\/21\/2016(3)",
 * "score":"0.00"
 * },
 * {
 * "date":"8\/20\/2016(2)",
 * "score":"0.00"
 * },
 * {
 * "date":"8\/20\/2016(1)",
 * "score":"0.00"
 * }
 * ]
 * }
 * }
 */
public class DailyReportExpanded {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataOfDailyReportExpanded dailyReportDateAndScores;

    public DailyReportExpanded() {
    }

    public DailyReportExpanded(String status, String message, DataOfDailyReportExpanded dailyReportDateAndScores) {
        this.status = status;
        this.message = message;
        this.dailyReportDateAndScores = dailyReportDateAndScores;
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

    public DataOfDailyReportExpanded getDailyReportDateAndScores() {
        return dailyReportDateAndScores;
    }

    public void setDailyReportDateAndScores(DataOfDailyReportExpanded dailyReportDateAndScores) {
        this.dailyReportDateAndScores = dailyReportDateAndScores;
    }
}
