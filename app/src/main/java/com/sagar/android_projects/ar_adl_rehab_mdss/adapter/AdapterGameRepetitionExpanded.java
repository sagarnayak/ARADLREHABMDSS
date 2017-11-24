package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetation;

import java.util.ArrayList;

/**
 * Created by SAGAR on 11/24/2017.
 */

public class AdapterGameRepetitionExpanded extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<GameRepetation> gameRepetations;
    private Context context;

    private boolean noMoreDataAvailable = true;

    public AdapterGameRepetitionExpanded(ArrayList<GameRepetation> gameRepetations, Context context) {
        this.gameRepetations = gameRepetations;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Const.ITEM) {
            return new AdapterGameRepetitionExpanded.ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.game_repetation_dashboard_item, parent, false));
        } else if (viewType == Const.PROGRESS) {
            return new AdapterGameRepetitionExpanded.ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == Const.ITEM) {
            ((ViewHolder) holder).appCompatImageViewMore.setVisibility(View.GONE);
            ((ViewHolder) holder).textViewHeading.setText(gameRepetations.get(position).getLevel());
            ((ViewHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolder) holder).recyclerView.setNestedScrollingEnabled(false);
            ((ViewHolder) holder).recyclerView.setAdapter(new AdapterGameRepetation(gameRepetations.get(position).getGameRepetationDataItems()));
        }
    }

    @Override
    public int getItemCount() {
        if (gameRepetations.size() == 0)
            return 0;
        if (noMoreDataAvailable)
            return gameRepetations.size();
        return gameRepetations.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < gameRepetations.size())
            return Const.ITEM;
        return Const.PROGRESS;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewHeading;
        private RecyclerView recyclerView;
        private AppCompatImageView appCompatImageViewMore;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHeading = itemView.findViewById(R.id.textview_heading_game_repetation_dashboard_item);
            recyclerView = itemView.findViewById(R.id.recyclerview_game_repetation_dashboard_item);
            appCompatImageViewMore = itemView.findViewById(R.id.appcompatimageview_game_repetition_more);
        }
    }

    public boolean isNoMoreDataAvailable() {
        return noMoreDataAvailable;
    }

    public void setNoMoreDataAvailable(boolean noMoreDataAvailable) {
        this.noMoreDataAvailable = noMoreDataAvailable;
    }
}
