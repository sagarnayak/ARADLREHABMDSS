package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparison;

import java.util.ArrayList;

public class AdapterGameComparison extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<GameComparison> gameComparisons;
    private Context context;

    private boolean noMoreDataAvailable = true;

    public AdapterGameComparison(ArrayList<GameComparison> gameComparisons, Context context) {
        this.gameComparisons = gameComparisons;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Const.ITEM) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.game_comparison_recyclerview_item, parent, false));
        } else if (viewType == Const.PROGRESS) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == Const.ITEM) {
            ((ViewHolder) holder).textviewDate.setText(gameComparisons.get(position).getDate());
            ((ViewHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolder) holder).recyclerView.setNestedScrollingEnabled(false);
            ((ViewHolder) holder).recyclerView
                    .setAdapter(new AdapterGameComparisonData(
                            gameComparisons.get(position).getGameComparisonDataItems()));
        }
    }

    @Override
    public int getItemCount() {
        if (gameComparisons.size() == 0)
            return 0;
        if (noMoreDataAvailable)
            return gameComparisons.size();
        return gameComparisons.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < gameComparisons.size())
            return Const.ITEM;
        return Const.PROGRESS;
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

    @SuppressWarnings("unused")
    public boolean isNoMoreDataAvailable() {
        return noMoreDataAvailable;
    }

    public void setNoMoreDataAvailable(boolean noMoreDataAvailable) {
        this.noMoreDataAvailable = noMoreDataAvailable;
    }
}
