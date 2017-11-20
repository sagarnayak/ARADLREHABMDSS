package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;

public class PatientDetailsGraph extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textViewName;
    private TextView textviewAgeAndGender;
    private TextView textViewCondition;
    private TextView textViewMobileAndEmail;
    private FloatingActionButton floatingActionButton;

    private DashboardData dashboardData;
    public static final String DASHBOARD_DATA = "DASHBOARD_DATA";

    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_AGE = "USER_AGE";
    public static final String USER_GENDER = "USER_GENDER";
    public static final String USER_CONDITION = "USER_CONDITION";
    public static final String USER_MOBILE_NUMBER = "USER_MOBILE_NUMBER";
    public static final String USER_EMAIL = "USER_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details_graph);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            dashboardData = new Gson().fromJson(getIntent().getStringExtra(DASHBOARD_DATA), DashboardData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.recyclerview_patient_detail_graph);
        textViewName = findViewById(R.id.textview_name_patient_details_graph);
        textviewAgeAndGender = findViewById(R.id.textview_age_and_gender_patient_details_graph);
        textViewCondition = findViewById(R.id.textview_condition_patient_details_graph);
        textViewMobileAndEmail = findViewById(R.id.textview_mobile_and_email_patient_details_graph);
        floatingActionButton = findViewById(R.id.floating_action_button_patient_details_graph);

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            gotoDashboardTables();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoDashboardTables();
    }

    private void gotoDashboardTables() {
        startActivity(new Intent(this, PatientDetailsTables.class)
                .putExtra(PatientDetailsTables.DASHBOARD_DATA, new Gson().toJson(dashboardData))
                .putExtra(PatientDetailsTables.USER_ID, getIntent().getStringExtra(USER_ID))
                .putExtra(PatientDetailsTables.USER_NAME, getIntent().getStringExtra(USER_NAME))
                .putExtra(PatientDetailsTables.USER_AGE, getIntent().getStringExtra(USER_AGE))
                .putExtra(PatientDetailsTables.USER_GENDER, getIntent().getStringExtra(USER_GENDER))
                .putExtra(PatientDetailsTables.USER_CONDITION, getIntent().getStringExtra(USER_CONDITION))
                .putExtra(PatientDetailsTables.USER_MOBILE_NUMBER, getIntent().getStringExtra(USER_MOBILE_NUMBER))
                .putExtra(PatientDetailsTables.USER_EMAIL, getIntent().getStringExtra(USER_EMAIL))
        );
        finish();
    }
}
