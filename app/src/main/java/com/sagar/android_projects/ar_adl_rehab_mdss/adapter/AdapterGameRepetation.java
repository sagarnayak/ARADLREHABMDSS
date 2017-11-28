package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetationDataItem;

import java.util.ArrayList;


public class AdapterGameRepetation extends RecyclerView.Adapter<AdapterGameRepetation.ViewHolder> {
    private ArrayList<GameRepetationDataItem> gameRepetationDataItems;

    AdapterGameRepetation(ArrayList<GameRepetationDataItem> gameRepetationDataItems) {
        this.gameRepetationDataItems = gameRepetationDataItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_repetation_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewScore.setText(gameRepetationDataItems.get(position).getScore());
        holder.textViewReps.setText(gameRepetationDataItems.get(position).getRep());
    }

    @Override
    public int getItemCount() {
        return gameRepetationDataItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewScore;
        private TextView textViewReps;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewScore = itemView.findViewById(R.id.textview_score_game_repetation_recyclerview_item);
            textViewReps = itemView.findViewById(R.id.textview_reps_game_repetation_recyclerview_item);
        }
    }
}
