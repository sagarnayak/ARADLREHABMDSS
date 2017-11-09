package com.sagar.android_projects.ar_adl_rehab_mdss.pojo;

/**
 * Created by sagar on 11/9/2017.
 */

public class PatientListPojo {
    private String name;
    private String age;
    private String gender;
    private String condition;
    private String mobileNumber;
    private String email;

    public PatientListPojo() {
    }

    public PatientListPojo(String name, String age, String gender, String condition, String mobileNumber, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.condition = condition;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
