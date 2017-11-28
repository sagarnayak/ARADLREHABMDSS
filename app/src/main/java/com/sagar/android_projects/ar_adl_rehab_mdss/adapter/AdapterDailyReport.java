package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport.DailyReportDateAndScore;

import java.util.ArrayList;


public class AdapterDailyReport extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DailyReportDateAndScore> dailyReportDateAndScore;

    private boolean noMoreDataAvailable = true;

    public AdapterDailyReport(ArrayList<DailyReportDateAndScore> dailyReportDateAndScore) {
        this.dailyReportDateAndScore = dailyReportDateAndScore;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Const.ITEM) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.daily_report_recyclerview_item, parent, false));
        } else if (viewType == Const.PROGRESS) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == Const.ITEM) {
            ((ViewHolder) holder).textViewDate.setText(dailyReportDateAndScore.get(position).getDate());
            ((ViewHolder) holder).textViewScore.setText(dailyReportDateAndScore.get(position).getScore());
        }
    }

    @Override
    public int getItemCount() {
        if (dailyReportDateAndScore.size() == 0)
            return 0;
        if (noMoreDataAvailable)
            return dailyReportDateAndScore.size();
        return dailyReportDateAndScore.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < dailyReportDateAndScore.size())
            return Const.ITEM;
        return Const.PROGRESS;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDate;
        private TextView textViewScore;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewDate = itemView.findViewById(R.id.textview_date_daily_report_recyclerview_item);
            textViewScore = itemView.findViewById(R.id.textview_score_daily_report_recyclerview_item);
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
