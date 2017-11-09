package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.pojo.PatientListPojo;

import java.util.ArrayList;

/**
 * Created by sagar on 11/9/2017.
 */
public class AdapterPatientList extends RecyclerView.Adapter<AdapterPatientList.ViewHolder> {

    private ArrayList<PatientListPojo> patientListPojos;

    public AdapterPatientList(ArrayList<PatientListPojo> patientListPojos) {
        this.patientListPojos = patientListPojos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return patientListPojos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayoutContainer;
        private TextView textViewName;
        private TextView textViewAgeAndGender;
        private TextView textViewCondition;
        private TextView textViewMobileAndEmail;

        ViewHolder(View itemView) {
            super(itemView);

            constraintLayoutContainer = itemView.findViewById(R.id.constraintlayout_patient_list_item_container);
            textViewName = itemView.findViewById(R.id.textview_patient_name);
            textViewAgeAndGender = itemView.findViewById(R.id.textview_patient_age_and_gender);
            textViewMobileAndEmail = itemView.findViewById(R.id.textview_patient_mobile_and_email);
        }
    }
}
