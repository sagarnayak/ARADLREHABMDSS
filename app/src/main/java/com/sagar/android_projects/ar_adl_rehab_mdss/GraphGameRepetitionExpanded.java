package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetitionExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.NetworkUtil;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraphGameRepetitionExpanded extends AppCompatActivity {

    private LineChart lineChart;
    @SuppressWarnings("FieldCanBeLocal")
    private TextView textViewXAxisValue;
    @SuppressWarnings("FieldCanBeLocal")
    private TextView textViewYAxisValue;

    private String fromDate;
    private String toDate;

    public static final String TITLE = "TITLE";
    public static final String USER_ID = "USER_ID";
    public static final String GAME_ID = "GAME_ID";

    ArrayList<Entry> dats = null;
    LineDataSet dataSet;
    ArrayList<ILineDataSet> lineDataSets = null;

    private GameRepetitionExpanded gameRepetitionExpanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_game_repetition_expanded);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(getIntent().getStringExtra(TITLE));
        }

        lineChart = findViewById(R.id.linechart_game_repetition_expanded);
        textViewXAxisValue = findViewById(R.id.textview_x_axis_lavel_game_repetition_expanded);
        textViewYAxisValue = findViewById(R.id.textview_y_axis_lavel_game_repetition_expanded);

        textViewYAxisValue.setRotation(-90);
        textViewXAxisValue.setText(getString(R.string.date));
        textViewYAxisValue.setText(getString(R.string.mean_score));

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
                            Toast.makeText(GraphGameRepetitionExpanded.this, "From date can not be greater then To date", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            fromDate = DateUtil.formatDateForFilter(dayOfMonth, monthOfYear, year);
                            toDate = DateUtil.formatDateForFilter(dayOfMonthEnd, monthOfYearEnd, yearEnd);
                            getDataFromServer(
                                    getIntent().getStringExtra(USER_ID),
                                    getIntent().getStringExtra(GAME_ID),
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

    private void getDataFromServer(String userId, String gameId, String offset, String count, String from, String to) {
        if (!NetworkUtil.isConnected(this)) {
            Toast.makeText(this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        ((AppSingleton) getApplicationContext())
                .getApiInterface()
                .gameRepetition(userId, gameId, offset, count, from, to)
                .enqueue(new Callback<GameRepetitionExpanded>() {
                    @Override
                    public void onResponse(Call<GameRepetitionExpanded> call, Response<GameRepetitionExpanded> response) {
                        if (response.isSuccessful()) {
                            gameRepetitionExpanded = response.body();
                            setDataToGraph(response.body());
                            showFilterRangeIfApplicable();
                        } else {
                            Toast.makeText(GraphGameRepetitionExpanded.this, "failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GameRepetitionExpanded> call, Throwable t) {
                        Toast.makeText(GraphGameRepetitionExpanded.this, "error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void setDataToGraph(final GameRepetitionExpanded dataToGraph) {
        int sizeOfData = 0;
        if (dataToGraph.getGameRepetations().size() <= 1) {
            Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
            return;
        }
        lineDataSets = new ArrayList<>();
        dats = new ArrayList<>();
        for (int i = 0; i < dataToGraph.getGameRepetations().size(); i++) {
            for (int j = 0; j < dataToGraph.getGameRepetations().get(i).getGameRepetationDataItems().size(); j++) {
                dats.add(new Entry((float) sizeOfData,
                        Float.parseFloat(dataToGraph.getGameRepetations().get(i).getGameRepetationDataItems().get(j).getScore())));
                sizeOfData++;
            }
        }

        dataSet = new LineDataSet(dats, "Label");
        dataSet.setColor(ResourcesCompat.getColor(getResources(), R.color.red_700, null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
        dataSet.setCircleColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
        dataSet.setLineWidth(2f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        lineDataSets.add(dataSet);
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
        lineChart.zoom(sizeOfData / 10,
                0f,
                sizeOfData,
                0f);
        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setEnabled(false);
        lineChart.moveViewToX(sizeOfData);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        lineChart.getLegend().setEnabled(false);
        lineChart.setViewPortOffsets(100f, 50f, 100f, 100f);

        final int finalSizeOfData = sizeOfData;

        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int valueint = (int) value;
                int valueOfI = 0;
                int valueOfJ = 0;
                for (int i = 0; i < finalSizeOfData; i++) {
                    if (valueint < dataToGraph.getGameRepetations().get(0).getGameRepetationDataItems().size()) {
                        valueOfI = 0;
                        valueOfJ = valueint;
                        break;
                    } else if (valueint < (i * dataToGraph.getGameRepetations().get(0).getGameRepetationDataItems().size())) {
                        valueOfI = i - 1;
                        valueOfJ = valueint - (dataToGraph.getGameRepetations().get(0).getGameRepetationDataItems().size() * (i - 1));
                        break;
                    }
                }
                String[] lavelAry = gameRepetitionExpanded.getGameRepetations().get(valueOfI).getLevel().split(" ");
                String lavel = "";
                for (int k = 1; k < lavelAry.length; k++) {
                    lavel += lavelAry[k];
                }
                lavel = lavel.trim();
                lavel += "(" +
                        gameRepetitionExpanded.getGameRepetations().get(valueOfI).getGameRepetationDataItems().get(valueOfJ).getRep()
                        + ")";
                return lavel;
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

}
