package com.sagar.android_projects.ar_adl_rehab_mdss.util;

@SuppressWarnings("unused")
public class Random {

    @SuppressWarnings("unused")
    public static int getRandomNumber() {
        java.util.Random r = new java.util.Random();
        int lowerBound = 1;
        int upperBound = 100;
        return r.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
