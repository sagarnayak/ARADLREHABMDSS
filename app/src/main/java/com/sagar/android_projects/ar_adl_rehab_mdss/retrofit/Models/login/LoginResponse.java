package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */
public class LoginResponse {

    public enum Response {
        SUCCESS("Success"),
        FAIL("Fail");

        private String code;

        Response(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    @SerializedName("result")
    private String result;

    public LoginResponse() {
    }

    public LoginResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
