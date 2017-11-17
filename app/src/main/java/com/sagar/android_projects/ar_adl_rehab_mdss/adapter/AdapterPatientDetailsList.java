package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;

/**
 * Created by sagar on 11/16/2017.
 */
public class AdapterPatientDetailsList extends RecyclerView.Adapter<AdapterPatientDetailsList.ViewHolder> {

    private Context context;

    public AdapterPatientDetailsList(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_report_dashboard_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(new AdapterDailyReport());
        holder.recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recyclerview_daily_report_dashboard_item);
        }
    }
}
