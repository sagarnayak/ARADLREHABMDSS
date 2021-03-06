package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterGameRepetitionExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.DateUtil;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetation;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetitionExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.NetworkUtil;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class GameRepetitionDetailReport extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private AppCompatImageView appCompatImageViewNoMoreData;

    public static final String TITLE = "TITLE";
    public static final String USER_ID = "USER_ID";
    public static final String GAME_ID = "GAME_ID";

    private LinearLayoutManager linearLayoutManager;
    private AdapterGameRepetitionExpanded adapterGameRepetitionExpanded;
    private ArrayList<GameRepetation> data;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private String fromDate = "";
    private String toDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_repetition_detail_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(getIntent().getStringExtra(TITLE));
        }

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_game_repetition_expanded);
        recyclerView = findViewById(R.id.recyclerview_game_repetition_expanded);
        appCompatImageViewNoMoreData = findViewById(R.id.appcompatimageview_no_more_Data_game_repetition_expanded);

        getDataFromServer(getIntent().getStringExtra(USER_ID), getIntent().getStringExtra(GAME_ID),
                "0", String.valueOf(Const.PAGE_SIZE), fromDate, toDate);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        getDataFromServer(getIntent().getStringExtra(USER_ID),
                                getIntent().getStringExtra(GAME_ID), String.valueOf(data.size()),
                                String.valueOf(Const.PAGE_SIZE), fromDate, toDate);
                    }
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                linearLayoutManager = null;
                fromDate = "";
                toDate = "";
                getDataFromServer(getIntent().getStringExtra(USER_ID),
                        getIntent().getStringExtra(GAME_ID), "0",
                        String.valueOf(Const.PAGE_SIZE), fromDate, toDate);
            }
        });
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

    @Override
    protected void onResume() {
        super.onResume();
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
                            Toast.makeText(GameRepetitionDetailReport.this, "From date can not be greater then To date", Toast.LENGTH_SHORT).show();
                        } else {
                            swipeRefreshLayout.setRefreshing(true);
                            fromDate = DateUtil.formatDateForFilter(dayOfMonth, monthOfYear, year);
                            toDate = DateUtil.formatDateForFilter(dayOfMonthEnd, monthOfYearEnd, yearEnd);
                            linearLayoutManager = null;
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
        isLoading = true;
        ((AppSingleton) getApplicationContext())
                .getApiInterface()
                .gameRepetition(userId, gameId, offset, count, from, to)
                .enqueue(new Callback<GameRepetitionExpanded>() {
                    @Override
                    public void onResponse(Call<GameRepetitionExpanded> call, Response<GameRepetitionExpanded> response) {
                        if (response.isSuccessful()) {
                            GameRepetitionExpanded gameRepetitionExpanded = response.body();
                            setDataToRecyclerview(gameRepetitionExpanded.getGameRepetations(),
                                    gameRepetitionExpanded.getGameRepetations().size() < Const.PAGE_SIZE);
                        } else {
                            Toast.makeText(GameRepetitionDetailReport.this, "failed to get data", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                        isLoading = false;
                    }

                    @Override
                    public void onFailure(Call<GameRepetitionExpanded> call, Throwable t) {
                        isLoading = false;
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(GameRepetitionDetailReport.this, "error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void setDataToRecyclerview(ArrayList<GameRepetation> dataToRecyclerview, boolean noMoreDataAvailable) {
        checkIfLastPage(noMoreDataAvailable);
        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(GameRepetitionDetailReport.this);
            data = new ArrayList<>();
            data.addAll(dataToRecyclerview);
            adapterGameRepetitionExpanded = new AdapterGameRepetitionExpanded(data,this);
            adapterGameRepetitionExpanded.setNoMoreDataAvailable(noMoreDataAvailable);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapterGameRepetitionExpanded);
        } else {
            data.addAll(dataToRecyclerview);
            adapterGameRepetitionExpanded.setNoMoreDataAvailable(noMoreDataAvailable);
            adapterGameRepetitionExpanded.notifyDataSetChanged();
        }
        showNoDataSignAsApplicable();
        showFilterRangeIfApplicable();
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

    private void showNoDataSignAsApplicable() {
        if (data.size() == 0)
            appCompatImageViewNoMoreData.setVisibility(View.VISIBLE);
        else
            appCompatImageViewNoMoreData.setVisibility(View.GONE);
    }

    private void checkIfLastPage(boolean noMoreDataAvailable) {
        isLastPage = noMoreDataAvailable;

    }

}
