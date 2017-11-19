package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterPatientDetailsList;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientDetailsTables extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textViewName;
    private TextView textviewAgeAndGender;
    private TextView textViewCondition;
    private TextView textViewMobileAndEmail;
    private ProgressBar progressBar;

    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details_tables);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview_patient_detail);
        textViewName = findViewById(R.id.textview_name_patient_details);
        textviewAgeAndGender = findViewById(R.id.textview_age_and_gender_patient_details);
        textViewCondition = findViewById(R.id.textview_condition_patient_details);
        textViewMobileAndEmail = findViewById(R.id.textview_mobile_and_email_patient_details);
        progressBar = findViewById(R.id.progressbar_patient_details);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ((AppSingleton) getApplicationContext()).getApiInterface()
                .dashboadData(getIntent().getStringExtra(USER_ID))
                .enqueue(new Callback<DashboardData>() {
                    @Override
                    public void onResponse(Call<DashboardData> call, Response<DashboardData> response) {
                        if (response.isSuccessful()) {
                            setDataToAdapter(response.body());
                        } else {
                            Toast.makeText(PatientDetailsTables.this, "failed to get data",
                                    Toast.LENGTH_SHORT).show();
                            finishActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<DashboardData> call, Throwable t) {
                        Toast.makeText(PatientDetailsTables.this, "failed to get data",
                                Toast.LENGTH_SHORT).show();
                        finishActivity();
                    }
                });
    }

    private void finishActivity() {
        finish();
    }

    private void setDataToAdapter(DashboardData dashboardData) {
        if (dashboardData.getData().getDailyReports() == null) {
            finish();
            return;
        }
        if (dashboardData.getData().getTrainingFrequencies() == null) {
            finish();
            return;
        }
        if (dashboardData.getData().getGameComparisons() == null) {
            finish();
            return;
        }
        if (dashboardData.getData().getGameRepetations() == null) {
            finish();
            return;
        }
        recyclerView.setAdapter(new AdapterPatientDetailsList(this, dashboardData));
    }

}
