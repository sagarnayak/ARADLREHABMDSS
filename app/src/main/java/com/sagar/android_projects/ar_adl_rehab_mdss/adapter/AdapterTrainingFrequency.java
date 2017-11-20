package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency.TrainingFrequency;

import java.util.ArrayList;

/**
 * Created by sagar on 11/17/2017.
 */
public class AdapterTrainingFrequency extends RecyclerView.Adapter<AdapterTrainingFrequency.ViewHolder> {

    private ArrayList<TrainingFrequency> trainingFrequencies;

    public AdapterTrainingFrequency(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_frequency_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewDate.setText(trainingFrequencies.get(position).getDate());
        holder.textViewReps.setText(trainingFrequencies.get(position).getReps());
    }

    @Override
    public int getItemCount() {
        return trainingFrequencies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDate;
        private TextView textViewReps;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewDate = itemView.findViewById(R.id.textview_date_training_freq_recyclerview_item);
            textViewReps = itemView.findViewById(R.id.textview_reps_training_freq_recyclerview_item);
        }
    }
}
