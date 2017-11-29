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
import android.widget.TextView;

import com.google.gson.Gson;
import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterPatientDetailsGraph;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;

public class PatientDetailsGraph extends AppCompatActivity implements AdapterPatientDetailsGraph.CallBackPatientGraph {

    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView recyclerView;
    @SuppressWarnings("FieldCanBeLocal")
    private TextView textViewName;
    @SuppressWarnings("FieldCanBeLocal")
    private TextView textviewAgeAndGender;
    @SuppressWarnings("FieldCanBeLocal")
    private TextView textViewCondition;
    @SuppressWarnings("FieldCanBeLocal")
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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDashboardTables();
            }
        });

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterPatientDetailsGraph(this, dashboardData, this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            gotoDashboardTables();
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
        startActivity(new Intent(PatientDetailsGraph.this, EditUser.class)
                .putExtra(EditUser.USER_ID, getIntent().getStringExtra(USER_ID)));
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

    @Override
    public void dailyReportClicked(String title, String gameId) {
        startActivity(new Intent(PatientDetailsGraph.this, GraphViewDailyReportExpanded.class)
                .putExtra(GraphViewDailyReportExpanded.TITLE, title)
                .putExtra(GraphViewDailyReportExpanded.USER_ID, getIntent().getStringExtra(USER_ID))
                .putExtra(GraphViewDailyReportExpanded.GAME_ID, gameId == null ? "1" : gameId));
    }

    @Override
    public void trainingFrequencyClicked() {
        startActivity(new Intent(PatientDetailsGraph.this, GraphViewTrainingFreqExpanded.class)
                .putExtra(GraphViewTrainingFreqExpanded.TITLE, String.valueOf("Training Frequency"))
                .putExtra(GraphViewTrainingFreqExpanded.USER_ID, getIntent().getStringExtra(USER_ID)));
    }

    @Override
    public void gameComparisonClicked() {
        startActivity(new Intent(PatientDetailsGraph.this, GraphViewGameComparison.class)
                .putExtra(GraphViewGameComparison.TITLE, String.valueOf("Game Comparison"))
                .putExtra(GraphViewGameComparison.USER_ID, getIntent().getStringExtra(USER_ID)));
    }

    @Override
    public void gameRepetitionClicked(String title, String gameId) {
        startActivity(new Intent(PatientDetailsGraph.this, GraphGameRepetitionExpanded.class)
                .putExtra(GraphGameRepetitionExpanded.TITLE, title)
                .putExtra(GraphGameRepetitionExpanded.USER_ID, getIntent().getStringExtra(USER_ID))
                .putExtra(GraphGameRepetitionExpanded.GAME_ID, gameId == null ? "1" : gameId));
    }
}
