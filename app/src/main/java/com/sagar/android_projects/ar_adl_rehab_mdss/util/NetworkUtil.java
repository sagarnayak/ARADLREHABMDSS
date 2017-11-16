package com.sagar.android_projects.ar_adl_rehab_mdss.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sagar on 11/16/2017.
 */
public class NetworkUtil {

    /**
     * method to check if device is connected to internet.
     * @param context context of activity
     * @return true of connected, false otherwise
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager CManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NInfo = CManager.getActiveNetworkInfo();
        return (NInfo != null && NInfo.isConnectedOrConnecting());
    }
}