package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparison;

import java.util.ArrayList;

/**
 * Created by sagar on 11/20/2017.
 */
public class AdapterGameComparison extends RecyclerView.Adapter<AdapterGameComparison.ViewHolder> {
    private ArrayList<GameComparison> gameComparisons;
    private Context context;

    public AdapterGameComparison(ArrayList<GameComparison> gameComparisons, Context context) {
        this.gameComparisons = gameComparisons;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_comparison_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textviewDate.setText(gameComparisons.get(position).getDate());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setNestedScrollingEnabled(false);
        holder.recyclerView.setAdapter(new AdapterGameComparisonData(gameComparisons.get(position).getGameComparisonDataItems()));
    }

    @Override
    public int getItemCount() {
        return gameComparisons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textviewDate;
        private RecyclerView recyclerView;

        ViewHolder(View itemView) {
            super(itemView);

            textviewDate = itemView.findViewById(R.id.textview_date_game_comparison_recyclerview_item);
            recyclerView = itemView.findViewById(R.id.recyclerview_data_game_comparison_recyclerview_item);
        }
    }
}
