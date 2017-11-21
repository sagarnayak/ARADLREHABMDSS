package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterPatientDetailsList;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientDetailsTables extends AppCompatActivity implements AdapterPatientDetailsList.CallBackPatientDetails {

    private RecyclerView recyclerView;
    private TextView textViewName;
    private TextView textviewAgeAndGender;
    private TextView textViewCondition;
    private TextView textViewMobileAndEmail;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;

    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_AGE = "USER_AGE";
    public static final String USER_GENDER = "USER_GENDER";
    public static final String USER_CONDITION = "USER_CONDITION";
    public static final String USER_MOBILE_NUMBER = "USER_MOBILE_NUMBER";
    public static final String USER_EMAIL = "USER_EMAIL";

    private DashboardData dashboardData;
    public static final String DASHBOARD_DATA = "DASHBOARD_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details_tables);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            dashboardData = new Gson().fromJson(getIntent().getStringExtra(DASHBOARD_DATA), DashboardData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.recyclerview_patient_detail);
        textViewName = findViewById(R.id.textview_name_patient_details);
        textviewAgeAndGender = findViewById(R.id.textview_age_and_gender_patient_details);
        textViewCondition = findViewById(R.id.textview_condition_patient_details);
        textViewMobileAndEmail = findViewById(R.id.textview_mobile_and_email_patient_details);
        progressBar = findViewById(R.id.progressbar_patient_details);
        floatingActionButton = findViewById(R.id.floating_action_button_patient_details_tables);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int resId = R.anim.layout_anim_slide_from_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(PatientDetailsTables.this, resId);
        recyclerView.setLayoutAnimation(animation);

        textViewName.setText(getIntent().getStringExtra(USER_NAME));
        textviewAgeAndGender.setText(String.valueOf(getIntent().getStringExtra(USER_AGE) + "," +
                getIntent().getStringExtra(USER_GENDER)));
        textViewCondition.setText(getIntent().getStringExtra(USER_CONDITION));
        textViewMobileAndEmail.setText(String.valueOf(getIntent().getStringExtra(USER_MOBILE_NUMBER) + "," +
                getIntent().getStringExtra(USER_EMAIL)));

        if (getSupportActionBar() != null) {
            setTitle(getIntent().getStringExtra(USER_NAME));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.hide();
                } else if (dy < 0 && floatingActionButton.getVisibility() != View.VISIBLE) {
                    floatingActionButton.show();
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGraphView(dashboardData);
            }
        });

        if (dashboardData == null)
            getDataFromWebService();
        else
            setDataToAdapter(dashboardData);
    }

    private void finishActivity() {
        finish();
    }

    private void getDataFromWebService() {
        if (!NetworkUtil.isConnected(PatientDetailsTables.this)) {
            Toast.makeText(PatientDetailsTables.this, "Please connect to internet", Toast.LENGTH_SHORT).show();
            return;
        }
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
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<DashboardData> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(PatientDetailsTables.this, "failed to get data",
                                Toast.LENGTH_SHORT).show();
                        finishActivity();
                    }
                });
    }

    private void setDataToAdapter(DashboardData dashboardData) {
        progressBar.setVisibility(View.GONE);
        if (dashboardData.getData().getDailyReports() == null ||
                dashboardData.getData().getDailyReports().size() == 0) {
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (dashboardData.getData().getTrainingFrequencies() == null ||
                dashboardData.getData().getTrainingFrequencies().size() == 0) {
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (dashboardData.getData().getGameComparisons() == null ||
                dashboardData.getData().getGameComparisons().size() == 0) {
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (dashboardData.getData().getGameRepetations() == null ||
                dashboardData.getData().getGameRepetations().size() == 0) {
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        this.dashboardData = dashboardData;
        recyclerView.setAdapter(new AdapterPatientDetailsList(this, dashboardData, this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    private void gotoGraphView(DashboardData dashboardData) {
        startActivity(new Intent(this, PatientDetailsGraph.class)
                .putExtra(PatientDetailsGraph.DASHBOARD_DATA, new Gson().toJson(dashboardData))
                .putExtra(PatientDetailsTables.USER_ID, getIntent().getStringExtra(USER_ID))
                .putExtra(PatientDetailsTables.USER_NAME, getIntent().getStringExtra(USER_NAME))
                .putExtra(PatientDetailsTables.USER_AGE, getIntent().getStringExtra(USER_AGE))
                .putExtra(PatientDetailsTables.USER_GENDER, getIntent().getStringExtra(USER_GENDER))
                .putExtra(PatientDetailsTables.USER_CONDITION, getIntent().getStringExtra(USER_CONDITION))
                .putExtra(PatientDetailsTables.USER_MOBILE_NUMBER, getIntent().getStringExtra(USER_MOBILE_NUMBER))
                .putExtra(PatientDetailsTables.USER_EMAIL, getIntent().getStringExtra(USER_EMAIL))
        );
        finishActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishActivity();
            return true;
        } else if (item.getItemId() == R.id.action_edit) {
            editUser();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient_details, menu);
        return true;
    }

    private void editUser() {

    }

    @Override
    public void dailyReportClicked() {
        startActivity(new Intent(PatientDetailsTables.this, DailyDetailReport.class));
    }

    @Override
    public void trainingFrequencyClicked() {

    }

    @Override
    public void gameComparisonClicked() {

    }

    public void gameRepetitionClicked() {

    }
}
