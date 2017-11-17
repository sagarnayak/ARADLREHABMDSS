package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterPatientDetailsList;

public class PatientDetailsTables extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textViewName;
    private TextView textviewAgeAndGender;
    private TextView textViewCondition;
    private TextView textViewMobileAndEmail;
    private ProgressBar progressBar;

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

        recyclerView.setAdapter(new AdapterPatientDetailsList(this));
    }

}
