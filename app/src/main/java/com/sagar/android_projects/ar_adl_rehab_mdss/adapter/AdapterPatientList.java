package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user.User;

import java.util.ArrayList;

import static com.sagar.android_projects.ar_adl_rehab_mdss.core.Const.ITEM;
import static com.sagar.android_projects.ar_adl_rehab_mdss.core.Const.PROGRESS;

/**
 * Created by sagar on 11/9/2017.
 */
public class AdapterPatientList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<User> users;

    private boolean isNoMoreDataAvailable = true;

    private AdapterPatientListCallback adapterPatientListCallback;

    public AdapterPatientList(ArrayList<User> users, AdapterPatientListCallback adapterPatientListCallback) {
        this.users = users;
        this.adapterPatientListCallback = adapterPatientListCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM)
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list_item, parent, false));
        if (viewType == PROGRESS)
            return new Progress(LayoutInflater.from(parent.getContext()).inflate(R.layout.progress, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdapterPatientList.ViewHolder) {
            ((ViewHolder) holder).textViewName.setText(users.get(position).getName());
            ((ViewHolder) holder).textViewAgeAndGender.setText(String.valueOf(users.get(position).getAge() + "," + users.get(position).getGender()));
            ((ViewHolder) holder).textViewCondition.setText(users.get(position).getCondition());
            ((ViewHolder) holder).textViewMobileAndEmail.setText(String.valueOf(users.get(position).getPhone() + "," + users.get(position).getEmail()));
        }
    }

    @Override
    public int getItemCount() {
        if (isNoMoreDataAvailable)
            return users.size();
        return users.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < users.size()) {
            return ITEM;
        }
        return PROGRESS;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayoutContainer;
        private TextView textViewName;
        private TextView textViewAgeAndGender;
        private TextView textViewCondition;
        private TextView textViewMobileAndEmail;

        ViewHolder(View itemView) {
            super(itemView);

            constraintLayoutContainer = itemView.findViewById(R.id.constraintlayout_patient_list_item_container);
            textViewName = itemView.findViewById(R.id.textview_patient_name);
            textViewAgeAndGender = itemView.findViewById(R.id.textview_patient_age_and_gender);
            textViewMobileAndEmail = itemView.findViewById(R.id.textview_patient_mobile_and_email);

            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterPatientListCallback.itemClickedInAdapterPatientList(users.get(getAdapterPosition()).getUserId());
                }
            });
        }
    }

    class Progress extends RecyclerView.ViewHolder {

        public Progress(View itemView) {
            super(itemView);
        }
    }

    public void setNoMoreDataAvailable(boolean noMoreDataAvailable) {
        this.isNoMoreDataAvailable = noMoreDataAvailable;
    }

    public interface AdapterPatientListCallback {
        void itemClickedInAdapterPatientList(String userId);
    }
}
