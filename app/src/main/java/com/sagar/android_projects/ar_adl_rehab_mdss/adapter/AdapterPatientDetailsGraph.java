package com.sagar.android_projects.ar_adl_rehab_mdss.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;

import java.util.ArrayList;
import java.util.Random;

public class AdapterPatientDetailsGraph extends RecyclerView.Adapter<AdapterPatientDetailsGraph.ViewHolder> {

    private Context context;
    private DashboardData dashboardData;
    private CallBackPatientGraph callBackPatientGraph;

    private static final int DAILY_REPORT = 123;
    private static final int TRAINING_FREQ = 124;
    private static final int GAME_COMP = 125;
    private static final int GAME_REP = 126;

    public AdapterPatientDetailsGraph(Context context, DashboardData dashboardData, CallBackPatientGraph callBackPatientGraph) {
        this.context = context;
        this.dashboardData = dashboardData;
        this.callBackPatientGraph = callBackPatientGraph;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_details_graph_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ArrayList<Entry> dats = null;
        LineDataSet dataSet;
        ArrayList<ILineDataSet> lineDataSets = null;
        if (getItemViewType(position) == DAILY_REPORT) {
            holder.gameId = dashboardData.getData().getDailyReports().get(position).getGameId();
            lineDataSets = new ArrayList<>();
            holder.textViewLavel.setText(dashboardData.getData().getDailyReports().get(position).getName());
            dats = new ArrayList<>();
            for (int i = 0; i < dashboardData.getData().getDailyReports().get(position).getData().size(); i++) {
                dats.add(new Entry(Float.parseFloat(String.valueOf(i)),
                        Float.parseFloat(dashboardData.getData().getDailyReports().get(position).getData().get(i).getScore())));
            }
            dataSet = new LineDataSet(dats, "Label");
            dataSet.setColor(ResourcesCompat.getColor(context.getResources(), R.color.red_700, null));
            dataSet.setValueTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
            dataSet.setCircleColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
            dataSet.setLineWidth(2f);
            dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            lineDataSets.add(dataSet);
        } else if (getItemViewType(position) == TRAINING_FREQ) {
            lineDataSets = new ArrayList<>();
            holder.textViewLavel.setText(String.valueOf("Training Frequency"));
            dats = new ArrayList<>();
            for (int i = 0; i < dashboardData.getData().getTrainingFrequencies().size(); i++) {
                dats.add(new Entry(Float.parseFloat(String.valueOf(i)),
                        Float.parseFloat(dashboardData.getData().getTrainingFrequencies().get(i).getReps())));
            }
            dataSet = new LineDataSet(dats, "Label");
            dataSet.setColor(ResourcesCompat.getColor(context.getResources(), R.color.red_700, null));
            dataSet.setValueTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
            dataSet.setCircleColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
            dataSet.setLineWidth(2f);
            dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            lineDataSets.add(dataSet);
        } else if (getItemViewType(position) == GAME_COMP) {
            holder.textViewLavel.setText(String.valueOf("Game Comparison"));
            lineDataSets = new ArrayList<>();
            int gameIndexBeingOperated = 0;
            for (int i = 0; i < dashboardData.getData().getGameComparisons().get(0).getGameComparisonDataItems().size(); i++) {
                dats = new ArrayList<>();
                String lavel = "";
                for (int j = 0; j < dashboardData.getData().getGameComparisons().size(); j++) {
                    ////////////////////////////////////////////////////////////////////////////////
                    /*
                    for random value.
                    use this in demo app only
                     */
                    /*dats.add(new Entry(Float.parseFloat(String.valueOf(j)),
                            Float.parseFloat(dashboardData.getData().getGameComparisons().get(j).getGameComparisonDataItems().get(gameIndexBeingOperated).getReps())));*/
                    ////////////////////////////////////////////////////////////////////////////////
                    lavel = dashboardData.getData().getGameComparisons().get(j).getGameComparisonDataItems().get(gameIndexBeingOperated).getName();
                    dats.add(new Entry(Float.parseFloat(String.valueOf(j)),
                            Float.parseFloat(String.valueOf(getRandomNumber()))));
                }
                gameIndexBeingOperated++;
                dataSet = new LineDataSet(dats, lavel);
                dataSet.setColor(generateRandomColor());
                dataSet.setValueTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
                dataSet.setCircleColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
                dataSet.setLineWidth(2f);
                dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                lineDataSets.add(dataSet);
            }
        } else if (getItemViewType(position) == GAME_REP) {
            lineDataSets = new ArrayList<>();
            int positionToOperate = position - (dashboardData.getData().getDailyReports().size() + 1 + 1);
            holder.textViewLavel.setText(dashboardData.getData().getGameRepetations().get(positionToOperate).getLevel());
            dats = new ArrayList<>();
            for (int i = 0; i < dashboardData.getData().getGameRepetations().get(positionToOperate).getGameRepetationDataItems().size(); i++) {
                dats.add(new Entry(Float.parseFloat(String.valueOf(i)),
                        Float.parseFloat(dashboardData.getData().getGameRepetations().get(positionToOperate).getGameRepetationDataItems().get(i).getScore())));
            }
            dataSet = new LineDataSet(dats, "Label");
            dataSet.setColor(ResourcesCompat.getColor(context.getResources(), R.color.red_700, null));
            dataSet.setValueTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
            dataSet.setCircleColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
            dataSet.setLineWidth(2f);
            dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            lineDataSets.add(dataSet);
        }

        /*
        set data to graph
         */
        LineData lineData = new LineData(lineDataSets);
        holder.lineChart.setData(lineData);
        holder.lineChart.invalidate();
        holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        holder.lineChart.getAxisLeft().setDrawGridLines(false);
        holder.lineChart.getAxisRight().setDrawGridLines(false);
        holder.lineChart.getXAxis().setDrawGridLines(false);
        holder.lineChart.setDescription(null);

        YAxis yAxis = holder.lineChart.getAxisRight();
        yAxis.setEnabled(false);

        XAxis xAxis = holder.lineChart.getXAxis();
        xAxis.setGranularity(1f);
        if (getItemViewType(position) == GAME_COMP) {
            holder.lineChart.getLegend().setEnabled(true);
        } else {
            holder.lineChart.getLegend().setEnabled(false);
        }
        holder.lineChart.setViewPortOffsets(100f, 50f, 100f, 100f);
        holder.lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int valueint = (int) value;
                if (getItemViewType(position) == DAILY_REPORT) {
                    return String.valueOf(dashboardData.getData().getDailyReports().get(position).getData().get(valueint).getDate());
                } else if (getItemViewType(position) == TRAINING_FREQ) {
                    return String.valueOf(dashboardData.getData().getTrainingFrequencies().get(valueint).getDate());
                } else if (getItemViewType(position) == GAME_COMP) {
                    return String.valueOf(dashboardData.getData().getGameComparisons().get(valueint).getDate());
                } else if (getItemViewType(position) == GAME_REP) {
                    int positionToOperate = position - (dashboardData.getData().getDailyReports().size() + 1 + 1);
                    return String.valueOf(dashboardData.getData().getGameRepetations().get(positionToOperate).getGameRepetationDataItems().get(valueint).getRep());
                }
                return null;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardData.getData().getDailyReports().size() +
                1 +
                1 +
                dashboardData.getData().getGameRepetations().size();
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewLavel;
        private LineChart lineChart;
        private String gameId;
        private AppCompatImageView appCompatImageViewMore;

        ViewHolder(View itemView) {
            super(itemView);

            textViewLavel = itemView.findViewById(R.id.textview_lavel_patient_details_graph_item);
            lineChart = itemView.findViewById(R.id.linechart_patient_details_graph_item);
            appCompatImageViewMore = itemView.findViewById(R.id.appcompatimageview_graph_daily_report_more);

            appCompatImageViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (AdapterPatientDetailsGraph.this.getItemViewType(getAdapterPosition())) {
                        case DAILY_REPORT:
                            callBackPatientGraph.dailyReportClicked(
                                    textViewLavel.getText().toString().trim(),
                                    gameId
                            );
                            break;
                        case TRAINING_FREQ:
                            callBackPatientGraph.trainingFrequencyClicked();
                            break;
                        case GAME_COMP:
                            callBackPatientGraph.gameComparisonClicked();
                        case GAME_REP:
                            callBackPatientGraph.gameRepetitionClicked(textViewLavel.getText().toString(),
                                    gameId);
                    }
                }
            });
        }
    }

    private int getRandomNumber() {
        Random r = new Random();
        int lowerBound = 1;
        int upperBound = 100;
        return r.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public int generateRandomColor() {
        Random mRandom = new Random();
        // This is the base color which will be mixed with the generated one
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return Color.rgb(red, green, blue);
    }

    public interface CallBackPatientGraph {
        void dailyReportClicked(String title, String gameId);

        void trainingFrequencyClicked();

        void gameComparisonClicked();

        void gameRepetitionClicked(String title, String gameId);
    }
}
