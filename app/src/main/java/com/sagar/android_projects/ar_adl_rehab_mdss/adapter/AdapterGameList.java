package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game.Game;

import java.util.ArrayList;

import static com.sagar.android_projects.ar_adl_rehab_mdss.core.Const.ITEM;
import static com.sagar.android_projects.ar_adl_rehab_mdss.core.Const.PROGRESS;

public class AdapterGameList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Game> gameListPojos;

    private boolean isNoMoreDataAvailable = true;

    public AdapterGameList(ArrayList<Game> gameListPojos) {
        this.gameListPojos = gameListPojos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM)
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.game_list_item, parent, false));
        if (viewType == PROGRESS)
            return new Progress(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).textViewName.setText(gameListPojos.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        if (gameListPojos.size() == 0)
            return 0;
        if (isNoMoreDataAvailable)
            return gameListPojos.size();
        return gameListPojos.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < gameListPojos.size()) {
            return ITEM;
        }
        return PROGRESS;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @SuppressWarnings("unused")
        private ConstraintLayout constraintLayoutContainer;
        private TextView textViewName;

        ViewHolder(View itemView) {
            super(itemView);

            constraintLayoutContainer = itemView.findViewById(R.id.constraintlayout_game_list_item_container);
            textViewName = itemView.findViewById(R.id.textview_game_name);
        }
    }

    class Progress extends RecyclerView.ViewHolder {

        Progress(View itemView) {
            super(itemView);
        }
    }

    public void setNoMoreDataAvailable(boolean noMoreDataAvailable) {
        this.isNoMoreDataAvailable = noMoreDataAvailable;
    }
}
