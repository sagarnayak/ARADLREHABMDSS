package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterPatientList;
import com.sagar.android_projects.ar_adl_rehab_mdss.frags.GameListFragment;
import com.sagar.android_projects.ar_adl_rehab_mdss.frags.PatientListFragment;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.Keyword;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements AdapterPatientList.AdapterPatientListCallback {

    @SuppressWarnings("FieldCanBeLocal")
    private TabLayout tabLayout;
    @SuppressWarnings("FieldCanBeLocal")
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportFragmentManager() != null) {
            setTitle(getString(R.string.app_name));
        }

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        checkIfAllowed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            showLogout();
            return true;
        }
        return false;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PatientListFragment(), "Patients");
        adapter.addFragment(new GameListFragment(), "Games");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void showLogout() {
        final AlertDialog alertDialogLogout = new AlertDialog.Builder(Dashboard.this).create();
        alertDialogLogout.setTitle("Logout");
        alertDialogLogout.setMessage("Do you want to logout ?");
        alertDialogLogout.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                        alertDialogLogout.dismiss();
                    }
                });
        alertDialogLogout.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialogLogout.dismiss();
                    }
                });
        alertDialogLogout.show();
    }

    private void logout() {
        getSharedPreferences(Keyword.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
                .edit()
                .putBoolean(Keyword.IS_LOGGED_IN, false)
                .apply();
        startActivity(new Intent(Dashboard.this, Login.class));
        finish();
    }

    @Override
    public void itemClickedInAdapterPatientList(String userId, String userName, String age,
                                                String gender, String condition,
                                                String mobileNumber, String email) {
        startActivity(new Intent(Dashboard.this, PatientDetailsTables.class)
                .putExtra(PatientDetailsTables.USER_ID, userId)
                .putExtra(PatientDetailsTables.USER_NAME, userName)
                .putExtra(PatientDetailsTables.USER_AGE, age)
                .putExtra(PatientDetailsTables.USER_GENDER, gender)
                .putExtra(PatientDetailsTables.USER_CONDITION, condition)
                .putExtra(PatientDetailsTables.USER_MOBILE_NUMBER, mobileNumber)
                .putExtra(PatientDetailsTables.USER_EMAIL, email)
        );
    }

    @Override
    public void onBackPressed() {
        exitOnDoubleBackPress();
    }

    private static boolean isBackPressed = false;

    private void exitOnDoubleBackPress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isBackPressed)
                    finish();
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Dashboard.this, "Press again to exit", Toast.LENGTH_SHORT).show();
                        }
                    });
                    isBackPressed = true;
                    try {
                        Thread.sleep(2000);
                        isBackPressed = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void checkIfAllowed() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refForUser = database.getReference().child("allowed");

        refForUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        if (dataSnapshot.getValue().equals("no")) {
                            showNotAllowed();
                        }
                    } else {
                        refForUser.setValue("To be applied");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showNotAllowed() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Warning");
        alertDialog.setMessage("Your trail period is finished");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                "ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
