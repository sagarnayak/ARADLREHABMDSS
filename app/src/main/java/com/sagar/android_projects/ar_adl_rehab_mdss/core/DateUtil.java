package com.sagar.android_projects.ar_adl_rehab_mdss.core;

/**
 * Created by sagar on 11/22/2017.
 */
public class DateUtil {
    public static String formarDateForFilter(int dayOfMonth, int monthOfYear, int year) {
        return String.valueOf(
                (monthOfYear + 1)
                        + "/"
                        + dayOfMonth
                        + "/"
                        + year
        );
    }

}
