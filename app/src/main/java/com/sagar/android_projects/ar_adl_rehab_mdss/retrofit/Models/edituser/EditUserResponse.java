package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SAGAR on 11/25/2017.
 */

public class EditUserResponse {

    public enum Response {
        SUCCESS("success"),
        FAIL("fail");

        private String code;

        Response(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private String data;

    public EditUserResponse() {
    }

    public EditUserResponse(String status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}