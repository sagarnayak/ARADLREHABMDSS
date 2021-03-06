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
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;


public class AdapterPatientDetailsList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private DashboardData dashboardData;
    private CallBackPatientDetails callBackPatientDetails;

    private static final int DAILY_REPORT = 123;
    private static final int TRAINING_FREQ = 124;
    private static final int GAME_COMP = 125;
    private static final int GAME_REP = 126;

    public AdapterPatientDetailsList(Context context, DashboardData dashboardData, CallBackPatientDetails callBackPatientDetails) {
        this.context = context;
        this.dashboardData = dashboardData;
        this.callBackPatientDetails = callBackPatientDetails;
    }

    @Override
    public int getItemCount() {
        return dashboardData.getData().getDailyReports().size() +
                1 +
                1 +
                dashboardData.getData().getGameRepetations().size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DAILY_REPORT:
                return new ViewHolderDailyReport(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.daily_report_dashboard_item, parent, false));
            case TRAINING_FREQ:
                return new ViewHolderTrainingFreq(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.training_freq_dashboard_item, parent, false));
            case GAME_COMP:
                return new ViewHolderGameComparison(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.game_comparison_dashboard_item, parent, false));
            case GAME_REP:
                return new ViewHolderGameRepetition(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.game_repetation_dashboard_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderDailyReport) {
            ((ViewHolderDailyReport) holder)
                    .textViewHeadding
                    .setText(dashboardData.getData().getDailyReports().get(position).getName());
            ((ViewHolderDailyReport) holder)
                    .recyclerView
                    .setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolderDailyReport) holder)
                    .recyclerView
                    .setNestedScrollingEnabled(false);
            ((ViewHolderDailyReport) holder)
                    .recyclerView
                    .setAdapter(new AdapterDailyReport(dashboardData.getData().getDailyReports().get(position).getData()));
            ((ViewHolderDailyReport) holder)
                    .gameId = dashboardData.getData().getDailyReports().get(position).getGameId();
        }
        if (holder instanceof ViewHolderTrainingFreq) {
            ((ViewHolderTrainingFreq) holder)
                    .textViewHeadding
                    .setText(String.valueOf("Training Frequency"));
            ((ViewHolderTrainingFreq) holder)
                    .recyclerView
                    .setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolderTrainingFreq) holder)
                    .recyclerView
                    .setNestedScrollingEnabled(false);
            ((ViewHolderTrainingFreq) holder)
                    .recyclerView
                    .setAdapter(new AdapterTrainingFrequency(dashboardData.getData().getTrainingFrequencies()));
        }
        if (holder instanceof ViewHolderGameComparison) {
            ((ViewHolderGameComparison) holder)
                    .textViewHeading
                    .setText(String.valueOf("Game Comparison"));
            ((ViewHolderGameComparison) holder)
                    .recyclerView
                    .setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolderGameComparison) holder)
                    .recyclerView
                    .setNestedScrollingEnabled(false);
            ((ViewHolderGameComparison) holder)
                    .recyclerView
                    .setAdapter(new AdapterGameComparison(dashboardData.getData().getGameComparisons(), context));
        }
        if (holder instanceof ViewHolderGameRepetition) {
            int positionToOperate = position - (dashboardData.getData().getDailyReports().size() + 1 + 1);
            ((ViewHolderGameRepetition) holder)
                    .textViewHeading
                    .setText(dashboardData.getData().getGameRepetations().get(positionToOperate).getLevel());
            ((ViewHolderGameRepetition) holder)
                    .recyclerView
                    .setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolderGameRepetition) holder)
                    .recyclerView
                    .setNestedScrollingEnabled(false);
            ((ViewHolderGameRepetition) holder)
                    .recyclerView
                    .setAdapter(new AdapterGameRepetation(dashboardData.getData()
                            .getGameRepetations().get(positionToOperate).getGameRepetationDataItems()));
            ((ViewHolderGameRepetition) holder)
                    .gameName = dashboardData.getData().getGameRepetations().get(positionToOperate).getGameName();
            ((ViewHolderGameRepetition) holder)
                    .gameId = dashboardData.getData().getGameRepetations().get(positionToOperate).getGameId();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < dashboardData.getData().getDailyReports().size())
            return DAILY_REPORT;
        if (position == (dashboardData.getData().getDailyReports().size()))
            return TRAINING_FREQ;
        if (position == (dashboardData.getData().getDailyReports().size() + 1))
            return GAME_COMP;
        return GAME_REP;
    }

    class ViewHolderDailyReport extends RecyclerView.ViewHolder {

        private TextView textViewHeadding;
        private RecyclerView recyclerView;
        private AppCompatImageView appCompatImageViewMore;
        private String gameId;

        ViewHolderDailyReport(View itemView) {
            super(itemView);

            textViewHeadding = itemView.findViewById(R.id.textview_heading_daily_report_dashboard_item);
            recyclerView = itemView.findViewById(R.id.recyclerview_daily_report_dashboard_item);
            appCompatImageViewMore = itemView.findViewById(R.id.appcompatimageview_daily_report_more);

            appCompatImageViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBackPatientDetails.dailyReportClicked(textViewHeadding.getText().toString().trim(),
                            gameId);
                }
            });
        }
    }

    class ViewHolderTrainingFreq extends RecyclerView.ViewHolder {

        private TextView textViewHeadding;
        private RecyclerView recyclerView;
        private AppCompatImageView appCompatImageViewMore;

        ViewHolderTrainingFreq(View itemView) {
            super(itemView);

            textViewHeadding = itemView.findViewById(R.id.textview_heading_training_freq_dashboard_item);
            recyclerView = itemView.findViewById(R.id.recyclerview_training_freq_dashboard_item);
            appCompatImageViewMore = itemView.findViewById(R.id.appcompatimageview_training_freq_more);

            appCompatImageViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBackPatientDetails.trainingFrequencyClicked();
                }
            });
        }
    }

    class ViewHolderGameComparison extends RecyclerView.ViewHolder {

        private TextView textViewHeading;
        private RecyclerView recyclerView;
        private AppCompatImageView appCompatImageViewMore;

        ViewHolderGameComparison(View itemView) {
            super(itemView);

            textViewHeading = itemView.findViewById(R.id.textview_heading_game_comparison_dashboard_item);
            recyclerView = itemView.findViewById(R.id.recyclerview_game_comparison_dashboard_item);
            appCompatImageViewMore = itemView.findViewById(R.id.appcompatimageview_game_comparison_more);

            appCompatImageViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBackPatientDetails.gameComparisonClicked();
                }
            });
        }
    }

    class ViewHolderGameRepetition extends RecyclerView.ViewHolder {

        private TextView textViewHeading;
        private RecyclerView recyclerView;
        private AppCompatImageView appCompatImageViewMore;
        private String gameName;
        private String gameId;

        ViewHolderGameRepetition(View itemView) {
            super(itemView);

            textViewHeading = itemView.findViewById(R.id.textview_heading_game_repetation_dashboard_item);
            recyclerView = itemView.findViewById(R.id.recyclerview_game_repetation_dashboard_item);
            appCompatImageViewMore = itemView.findViewById(R.id.appcompatimageview_game_repetition_more);

            appCompatImageViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBackPatientDetails.gameRepetitionClicked(gameName, gameId);
                }
            });
        }
    }

    public interface CallBackPatientDetails {
        void dailyReportClicked(String title, String gameId);

        void trainingFrequencyClicked();

        void gameComparisonClicked();

        void gameRepetitionClicked(String title, String gameId);
    }
}
