package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport.DailyReport;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((AppSingleton) getApplicationContext())
                .getApiInterface()
                .dailyReport("1",
                        "0",
                        "0",
                        "10",
                        "21/8/2017",
                        "21/10/2017")
                .enqueue(new Callback<DailyReport>() {
                    @Override
                    public void onResponse(Call<DailyReport> call, Response<DailyReport> response) {

                    }

                    @Override
                    public void onFailure(Call<DailyReport> call, Throwable t) {

                    }
                });
    }

}
