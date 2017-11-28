package com.sagar.android_projects.ar_adl_rehab_mdss.core;


public class DateUtil {
    public static String formatDateForFilter(int dayOfMonth, int monthOfYear, int year) {
        return String.valueOf(
                (monthOfYear + 1)
                        + "/"
                        + dayOfMonth
                        + "/"
                        + year
        );
    }

}
