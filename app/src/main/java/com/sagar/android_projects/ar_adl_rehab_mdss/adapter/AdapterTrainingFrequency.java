package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency.TrainingFrequency;

import java.util.ArrayList;

/**
 * Created by sagar on 11/17/2017.
 */
public class AdapterTrainingFrequency extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TrainingFrequency> trainingFrequencies;

    private boolean noMoreDataAvailable = true;

    public AdapterTrainingFrequency(ArrayList<TrainingFrequency> trainingFrequencies) {
        this.trainingFrequencies = trainingFrequencies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Const.ITEM) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.training_frequency_recyclerview_item, parent, false));
        } else if (viewType == Const.PROGRESS) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == Const.ITEM) {
            ((ViewHolder) holder).textViewDate.setText(trainingFrequencies.get(position).getDate());
            ((ViewHolder) holder).textViewReps.setText(trainingFrequencies.get(position).getReps());
        }
    }

    @Override
    public int getItemCount() {
        if (trainingFrequencies.size() == 0)
            return 0;
        if (noMoreDataAvailable)
            return trainingFrequencies.size();
        return trainingFrequencies.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < trainingFrequencies.size())
            return Const.ITEM;
        return Const.PROGRESS;
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

    public boolean isNoMoreDataAvailable() {
        return noMoreDataAvailable;
    }

    public void setNoMoreDataAvailable(boolean noMoreDataAvailable) {
        this.noMoreDataAvailable = noMoreDataAvailable;
    }
}
