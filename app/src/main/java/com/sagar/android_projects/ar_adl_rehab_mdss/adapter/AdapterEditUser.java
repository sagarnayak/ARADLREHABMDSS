package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.UserDetailData;

import java.util.ArrayList;


public class AdapterEditUser extends RecyclerView.Adapter<AdapterEditUser.ViewHolder> {

    private ArrayList<UserDetailData> userDetailData;
    private Callback callback;
    private Context context;

    private boolean buttonActivated = true;

    public AdapterEditUser(ArrayList<UserDetailData> userDetailData, Callback callback, Context context) {
        this.userDetailData = userDetailData;
        this.callback = callback;
        this.context = context;
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
                    if (!buttonActivated)
                        return;
                    buttonActivated = false;
                    moveItemUp(getAdapterPosition());
                }
            });

            appCompatImageViewDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!buttonActivated)
                        return;
                    buttonActivated = false;
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
        updateNextGameIds();
        notifyItemMoved(position, (position - 1));
        waitAndNotifyDataSetChanged();
    }

    private void moveItemDown(int position) {
        UserDetailData userDetailDataTemp = userDetailData.get(position);
        userDetailData.remove(position);
        userDetailData.add(position + 1, userDetailDataTemp);
        updateNextGameIds();
        notifyItemMoved(position, (position + 1));
        waitAndNotifyDataSetChanged();
    }

    private void updateNextGameIds() {
        for (int i = 0; i < userDetailData.size(); i++) {
            if (i == (userDetailData.size() - 1)) {
                userDetailData.get(i).setNextGameId(String.valueOf(-1));
            } else {
                userDetailData.get(i).setNextGameId(userDetailData.get(i + 1).getGameId());
            }
        }
    }

    private void waitAndNotifyDataSetChanged() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                    buttonActivated = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface Callback {
        void editUser(int index);
    }

    public ArrayList<UserDetailData> getUserDetailData() {
        return userDetailData;
    }
}
