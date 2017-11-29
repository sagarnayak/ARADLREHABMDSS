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

/**
 * Created by SAGAR on 11/29/2017.
 */

public class AdapterAddGame extends RecyclerView.Adapter<AdapterAddGame.ViewHolder> {

    private ArrayList<Game> gameListPojos;
    private Callback callback;

    public AdapterAddGame(ArrayList<Game> gameListPojos, Callback callback) {
        this.gameListPojos = gameListPojos;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewName.setText(gameListPojos.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return gameListPojos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @SuppressWarnings("unused")
        private ConstraintLayout constraintLayoutContainer;
        private TextView textViewName;

        ViewHolder(View itemView) {
            super(itemView);

            constraintLayoutContainer = itemView.findViewById(R.id.constraintlayout_game_list_item_container);
            textViewName = itemView.findViewById(R.id.textview_game_name);

            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.clicked(gameListPojos.get(getAdapterPosition()).getName(),
                            gameListPojos.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface Callback {
        void clicked(String gameName, String gameId);
    }
}
