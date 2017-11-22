package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.UserDetailData;

import java.util.ArrayList;

/**
 * Created by sagar on 11/22/2017.
 */

public class AdapterEditUser extends RecyclerView.Adapter<AdapterEditUser.ViewHolder> {

    private ArrayList<UserDetailData> userDetailData;
    private Callback callback;

    public AdapterEditUser(ArrayList<UserDetailData> userDetailData, Callback callback) {
        this.userDetailData = userDetailData;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewGame.setText(String.valueOf(userDetailData.get(position).getGameName() + ", " + userDetailData.get(position).getGameId()));
        holder.textViewCount.setText(userDetailData.get(position).getCustomCount());
        holder.textViewTimes.setText(userDetailData.get(position).getCustomTime());

        holder.appCompatImageViewUp.setVisibility(View.VISIBLE);
        holder.appCompatImageViewDown.setVisibility(View.VISIBLE);
        if (position == 0)
            holder.appCompatImageViewUp.setVisibility(View.GONE);
        if (position == (userDetailData.size() - 1))
            holder.appCompatImageViewDown.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return userDetailData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewGame;
        private TextView textViewCount;
        private TextView textViewTimes;
        private AppCompatImageView appCompatImageViewEdit;
        private AppCompatImageView appCompatImageViewUp;
        private AppCompatImageView appCompatImageViewDown;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewGame = itemView.findViewById(R.id.textview_game_name_edit_user_item);
            textViewCount = itemView.findViewById(R.id.textview_count_value_edit_user_item);
            textViewTimes = itemView.findViewById(R.id.textview_time_value_edit_user_item);
            appCompatImageViewEdit = itemView.findViewById(R.id.appcompatiamgeview_edit_edit_user_item);
            appCompatImageViewUp = itemView.findViewById(R.id.appcompatiamgeview_up_arrow_edit_user_item);
            appCompatImageViewDown = itemView.findViewById(R.id.appcompatiamgeview_down_arrow_edit_user_item);

            appCompatImageViewUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveItemUp(getAdapterPosition());
                }
            });

            appCompatImageViewDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveItemDown(getAdapterPosition());
                }
            });

            appCompatImageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.editUser(getAdapterPosition());
                }
            });
        }
    }

    private void moveItemUp(int position) {
        UserDetailData userDetailDataTemp = userDetailData.get(position);
        userDetailData.remove(position);
        userDetailData.add(position - 1, userDetailDataTemp);
        if (updateNextGameIds()) return;
        notifyDataSetChanged();
    }

    private boolean updateNextGameIds() {
        for (int i = 0; i < userDetailData.size(); i++) {
            if (i == (userDetailData.size() - 1)) {
                userDetailData.get(i).setNextGameId(String.valueOf(-1));
                return true;
            }
            userDetailData.get(i).setNextGameId(userDetailData.get(1 + 1).getGameId());
        }
        return false;
    }

    private void moveItemDown(int position) {
        UserDetailData userDetailDataTemp = userDetailData.get(position);
        userDetailData.remove(position);
        userDetailData.add(position + 1, userDetailDataTemp);
        notifyDataSetChanged();
    }

    public interface Callback {
        void editUser(int index);
    }

    public ArrayList<UserDetailData> getUserDetailData() {
        return userDetailData;
    }

    public void setUserDetailData(ArrayList<UserDetailData> userDetailData) {
        this.userDetailData = userDetailData;
        notifyDataSetChanged();
    }
}
