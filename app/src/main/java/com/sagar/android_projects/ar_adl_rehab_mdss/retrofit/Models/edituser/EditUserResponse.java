package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser;

import com.google.gson.annotations.SerializedName;


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

    @SuppressWarnings("unused")
    public EditUserResponse() {
    }

    @SuppressWarnings("unused")
    public EditUserResponse(String status, String message, String data) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
