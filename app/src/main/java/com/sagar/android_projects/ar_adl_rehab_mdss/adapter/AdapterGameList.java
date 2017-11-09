package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.pojo.GameListPojo;

import java.util.ArrayList;

/**
 * Created by sagar on 11/9/2017.
 */
public class AdapterGameList extends RecyclerView.Adapter<AdapterGameList.ViewHolder> {

    private ArrayList<GameListPojo> gameListPojos;

    public AdapterGameList(ArrayList<GameListPojo> gameListPojos) {
        this.gameListPojos = gameListPojos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return gameListPojos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayoutContainer;
        private TextView textViewName;

        ViewHolder(View itemView) {
            super(itemView);

            constraintLayoutContainer = itemView.findViewById(R.id.constraintlayout_game_list_item_container);
            textViewName = itemView.findViewById(R.id.textview_game_name);
        }
    }
}
