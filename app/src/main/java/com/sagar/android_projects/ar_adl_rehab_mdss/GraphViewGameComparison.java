package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.DateUtil;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparisonExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency.TrainingFrequency;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.NetworkUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraphViewGameComparison extends AppCompatActivity {

    public static final String TITLE = "TITLE";
    public static final String USER_ID = "USER_ID";

    private ArrayList<TrainingFrequency> data;

    private String fromDate = "";
    private String toDate = "";

    private LineChart lineChart;

    ArrayList<Entry> dats = null;
    LineDataSet dataSet;
    ArrayList<ILineDataSet> lineDataSets = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view_game_comparison);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(getIntent().getStringExtra(TITLE));
        }

        lineChart = findViewById(R.id.linechart_game_comparison_expanded);

        showDatePickerDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_date, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_date) {
            showDatePickerDialog();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void showDatePickerDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
                        Calendar calendarFrom = Calendar.getInstance();
                        calendarFrom.set(Calendar.YEAR, year);
                        calendarFrom.set(Calendar.MONTH, monthOfYear);
                        calendarFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Calendar calendarTo = Calendar.getInstance();
                        calendarTo.set(Calendar.YEAR, yearEnd);
                        calendarTo.set(Calendar.MONTH, monthOfYearEnd);
                        calendarTo.set(Calendar.DAY_OF_MONTH, dayOfMonthEnd);
                        if (calendarFrom.getTimeInMillis() > calendarTo.getTimeInMillis()) {
                            Toast.makeText(GraphViewGameComparison.this, "From date can not be greater then To date", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            fromDate = DateUtil.formarDateForFilter(dayOfMonth, monthOfYear, year);
                            toDate = DateUtil.formarDateForFilter(dayOfMonthEnd, monthOfYearEnd, yearEnd);
                            getDataFromServer(
                                    getIntent().getStringExtra(USER_ID),
                                    String.valueOf(0),
                                    String.valueOf(Const.PAGE_SIZE),
                                    fromDate,
                                    toDate
                            );
                        }
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
        dpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.i("MDSS_OkHttpLog", "cancelled");
            }
        });
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void getDataFromServer(String userId, String offset, String count, String from, String to) {
        if (!NetworkUtil.isConnected(this)) {
            Toast.makeText(this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        ((AppSingleton) getApplicationContext())
                .getApiInterface()
                .gameComparison(userId, offset, count, from, to)
                .enqueue(new Callback<GameComparisonExpanded>() {
                    @Override
                    public void onResponse(Call<GameComparisonExpanded> call, Response<GameComparisonExpanded> response) {
                        if (response.isSuccessful()) {
                            setDataToGraph(response.body());
                            showFilterRangeIfApplicable();
                        } else {
                            Toast.makeText(GraphViewGameComparison.this, "failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GameComparisonExpanded> call, Throwable t) {
                        Toast.makeText(GraphViewGameComparison.this, "error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void setDataToGraph(final GameComparisonExpanded gameComparisonExpanded) {
        lineDataSets = new ArrayList<>();
        int gameIndexBeingOperated = 0;
        for (int i = 0; i < gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons().get(0).getGameComparisonDataItems().size(); i++) {
            dats = new ArrayList<>();
            String lavel = "";
            for (int j = 0; j < gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons().size(); j++) {
                dats.add(new Entry(Float.parseFloat(String.valueOf(j)),
                        Float.parseFloat(gameComparisonExpanded.getGameComparisonExpandedData()
                                .getGameComparisons().get(j).getGameComparisonDataItems().get(gameIndexBeingOperated).getReps())));
                lavel = gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons().get(j).getGameComparisonDataItems().get(gameIndexBeingOperated).getName();
                /*dats.add(new Entry(Float.parseFloat(String.valueOf(j)),
                        Float.parseFloat(String.valueOf(getRandomNumber()))));*/
            }
            gameIndexBeingOperated++;
            dataSet = new LineDataSet(dats, lavel);
            dataSet.setColor(com.sagar.android_projects.ar_adl_rehab_mdss.util.Color.generateRandomColor());
            dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
            dataSet.setCircleColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
            dataSet.setLineWidth(2f);
            dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            lineDataSets.add(dataSet);
        }

        LineData lineData = new LineData(lineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setDescription(null);

        /*
        total number of data / zoom = number of data showing
        800 / 160 = 5
         */
        lineChart.zoom(gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons().size() / 10,
                0f,
                gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons().size(),
                0f);
        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setEnabled(false);
        lineChart.moveViewToX(gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons().size());
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        lineChart.getLegend().setEnabled(true);
        lineChart.setViewPortOffsets(100f, 50f, 100f, 100f);
        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int valueint = (int) value;
                return String.valueOf(gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons().get(valueint).getDate());
            }
        });
    }

    private void showFilterRangeIfApplicable() {
        if (fromDate.length() != 0 && toDate.length() != 0) {
            if (getSupportActionBar() != null) {
                setTitle(String.valueOf(fromDate + "-" + toDate));
            }
        } else {
            if (getSupportActionBar() != null) {
                setTitle(getIntent().getStringExtra(TITLE));
            }
        }
    }

    private int getRandomNumber() {
        Random r = new Random();
        int lowerBound = 1;
        int upperBound = 100;
        return r.nextInt(upperBound - lowerBound) + lowerBound;
    }

}
