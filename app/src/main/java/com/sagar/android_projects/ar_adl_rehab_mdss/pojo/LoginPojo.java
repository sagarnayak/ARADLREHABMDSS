package com.sagar.android_projects.ar_adl_rehab_mdss.pojo;

/**
 * Created by sagar on 11/9/2017.
 */
public class LoginPojo {

    public enum ResponseCode {
        SUCCESS("1"),
        FAILED("2");

        private String responseCode;

        ResponseCode(String code) {
            this.responseCode = code;
        }

        public String getResponseCode() {
            return responseCode;
        }
    }

    private String response;

    public LoginPojo() {
    }

    public LoginPojo(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
