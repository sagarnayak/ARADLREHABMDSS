package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport.DailyReportDateAndScore;

import java.util.ArrayList;

/**
 * Created by sagar on 11/17/2017.
 */
public class AdapterDailyReport extends RecyclerView.Adapter<AdapterDailyReport.ViewHolder> {

    private ArrayList<DailyReportDateAndScore> dailyReportDateAndScore;

    public AdapterDailyReport(ArrayList<DailyReportDateAndScore> dailyReportDateAndScore) {
        this.dailyReportDateAndScore = dailyReportDateAndScore;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_report_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewDate.setText(dailyReportDateAndScore.get(position).getDate());
        holder.textViewScore.setText(dailyReportDateAndScore.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return dailyReportDateAndScore.size();
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
}
