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
import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterGameComparison;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.DateUtil;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparison;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparisonExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.NetworkUtil;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class GameComparisonDetailReport extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private AppCompatImageView appCompatImageViewNoMoreData;

    public static final String TITLE = "TITLE";
    public static final String USER_ID = "USER_ID";

    private LinearLayoutManager linearLayoutManager;
    private AdapterGameComparison adapterGameComparison;
    private ArrayList<GameComparison> data;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private String fromDate = "";
    private String toDate = "";

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_comparison_detail_report);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.post(new Runnable() {
                @Override
                public void run() {
                    toolbar.setTitle(getIntent().getStringExtra(TITLE));
                }
            });
        }

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_game_comparison_detail_expanded);
        recyclerView = findViewById(R.id.recyclerview_game_comparison_detail_expanded);
        appCompatImageViewNoMoreData = findViewById(R.id.appcompatimageview_no_more_Data_game_comparison_detail_expanded);

        getDataFromServer(getIntent().getStringExtra(USER_ID),
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
                                String.valueOf(data.size()),
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
                        "0", String.valueOf(Const.PAGE_SIZE), fromDate, toDate);
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
                            Toast.makeText(GameComparisonDetailReport.this, "From date can not be greater then To date", Toast.LENGTH_SHORT).show();
                        } else {
                            swipeRefreshLayout.setRefreshing(true);
                            fromDate = DateUtil.formarDateForFilter(dayOfMonth, monthOfYear, year);
                            toDate = DateUtil.formarDateForFilter(dayOfMonthEnd, monthOfYearEnd, yearEnd);
                            linearLayoutManager = null;
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
        from = "8/20/2016";
        isLoading = true;
        ((AppSingleton) getApplicationContext())
                .getApiInterface()
                .gameComparison(userId, offset, count, from, to)
                .enqueue(new Callback<GameComparisonExpanded>() {
                    @Override
                    public void onResponse(Call<GameComparisonExpanded> call, Response<GameComparisonExpanded> response) {
                        if (response.isSuccessful()) {
                            GameComparisonExpanded gameComparisonExpanded = response.body();
                            setDataToRecyclerview(gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons(),
                                    gameComparisonExpanded.getGameComparisonExpandedData().getGameComparisons().size() < Const.PAGE_SIZE);
                        } else {
                            Toast.makeText(GameComparisonDetailReport.this, "failed to get data", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                        isLoading = false;
                    }

                    @Override
                    public void onFailure(Call<GameComparisonExpanded> call, Throwable t) {
                        isLoading = false;
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(GameComparisonDetailReport.this, "error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void setDataToRecyclerview(ArrayList<GameComparison> dataToRecyclerview, boolean noMoreDataAvailable) {
        checkIfLastPage(noMoreDataAvailable);
        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(GameComparisonDetailReport.this);
            data = new ArrayList<>();
            data.addAll(dataToRecyclerview);
            adapterGameComparison = new AdapterGameComparison(data, GameComparisonDetailReport.this);
            adapterGameComparison.setNoMoreDataAvailable(noMoreDataAvailable);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapterGameComparison);
        } else {
            data.addAll(dataToRecyclerview);
            adapterGameComparison.setNoMoreDataAvailable(noMoreDataAvailable);
            adapterGameComparison.notifyDataSetChanged();
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
                toolbar.post(new Runnable() {
                    @Override
                    public void run() {
                        toolbar.setTitle(getIntent().getStringExtra(TITLE));
                    }
                });
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
