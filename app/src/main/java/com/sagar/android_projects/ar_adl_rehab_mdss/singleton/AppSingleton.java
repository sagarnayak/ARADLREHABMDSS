package com.sagar.android_projects.ar_adl_rehab_mdss.singleton;

import android.app.Application;
import android.util.Log;

import com.sagar.android_projects.ar_adl_rehab_mdss.BuildConfig;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.URLs;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.ApiInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppSingleton extends Application {
    private ApiInterface apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i(Const.LOG_TAG, String.valueOf(message));
                    }
                }
        );
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }
}
