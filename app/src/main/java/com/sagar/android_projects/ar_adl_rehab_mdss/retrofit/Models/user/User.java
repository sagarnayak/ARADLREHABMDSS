package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user;

import com.google.gson.annotations.SerializedName;


public class User {
    @SerializedName("userId")
    private String userId;
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private String age;
    @SerializedName("gender")
    private String gender;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("condition")
    private String condition;
    @SerializedName("severity")
    private String severity;

    @SuppressWarnings("unused")
    public User() {
    }

    public User(String userId, String name, String gender, String age, String phone, String email,
                String condition, String severity) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.condition = condition;
        this.severity = severity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    @SuppressWarnings("unused")
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    @SuppressWarnings("unused")
    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    @SuppressWarnings("unused")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    @SuppressWarnings("unused")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCondition() {
        return condition;
    }

    @SuppressWarnings("unused")
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @SuppressWarnings("unused")
    public String getSeverity() {
        return severity;
    }

    @SuppressWarnings("unused")
    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
