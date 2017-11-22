package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterEditUser;

public class EditUser extends AppCompatActivity implements AdapterEditUser.Callback {

    private RecyclerView recyclerView;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview_edit_user);
        bottomSheet = findViewById(R.id.bottom_sheet_edit_user);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showBottomSheetForUserEdit();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void editUser() {
        showBottomSheetForUserEdit();
    }

    private void showBottomSheetForUserEdit() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
