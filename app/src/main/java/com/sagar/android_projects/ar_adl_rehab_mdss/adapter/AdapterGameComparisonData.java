package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparisonDataItems;

import java.util.ArrayList;

public class AdapterGameComparisonData extends RecyclerView.Adapter<AdapterGameComparisonData.ViewHolder> {
    private ArrayList<GameComparisonDataItems> gameComparisonDataItems;

    AdapterGameComparisonData(ArrayList<GameComparisonDataItems> gameComparisonDataItems) {
        this.gameComparisonDataItems = gameComparisonDataItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_data_game_comparison_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewGameName.setText(gameComparisonDataItems.get(position).getName());
        holder.textViewScore.setText(gameComparisonDataItems.get(position).getGameScore());
    }

    @Override
    public int getItemCount() {
        return gameComparisonDataItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewGameName;
        private TextView textViewScore;

        ViewHolder(View itemView) {
            super(itemView);

            textViewGameName = itemView.findViewById(R.id.textview_game_name_recyclerview_data_game_comp);
            textViewScore = itemView.findViewById(R.id.textview_score_recyclerview_data_game_comp);
        }
    }
}
