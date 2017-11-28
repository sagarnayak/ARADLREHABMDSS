package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterEditUser;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.EditUserResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.UserDetailData;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.UserDetails;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.NetworkUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUser extends AppCompatActivity implements AdapterEditUser.Callback {

    private RecyclerView recyclerView;
    @SuppressWarnings("FieldCanBeLocal")
    private ConstraintLayout constraintLayoutBottomSheet;
    private TextView textViewGameName;
    private EditText editTextCount;
    private EditText editTextTime;
    @SuppressWarnings("FieldCanBeLocal")
    private Button buttonEditGame;

    public static final String USER_ID = "USER_ID";

    private BottomSheetBehavior bottomSheetBehavior;
    private ArrayList<UserDetailData> userDetailData;
    private AdapterEditUser adapterEditUser;
    private int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerview_edit_user);
        constraintLayoutBottomSheet = findViewById(R.id.bottom_sheet);
        textViewGameName = findViewById(R.id.textview_game_name_edit_game_bottom_sheet);
        editTextCount = findViewById(R.id.edittext_count_edit_game_bottom_sheet);
        editTextTime = findViewById(R.id.edittext_time_edit_game_bottom_sheet);
        buttonEditGame = findViewById(R.id.button_edit_game_edit_game_bottom_sheet);

        bottomSheetBehavior = BottomSheetBehavior.from(constraintLayoutBottomSheet);

        buttonEditGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetailData.get(selectedIndex).setCustomCount(editTextCount.getText().toString().trim());
                userDetailData.get(selectedIndex).setCustomTime(editTextTime.getText().toString().trim());
                adapterEditUser.notifyDataSetChanged();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        getDataFromServer();
    }

    @Override
    public void editUser(int index) {
        selectedIndex = index;
        userDetailData.clear();
        userDetailData.addAll(adapterEditUser.getUserDetailData());
        showBottomSheetForUserEdit(userDetailData.get(index).getGameName(),
                userDetailData.get(index).getCustomCount(),
                userDetailData.get(index).getCustomTime());
    }

    private void showBottomSheetForUserEdit(String gameName, String count, String time) {
        textViewGameName.setText(gameName);
        editTextCount.setText(count);
        editTextTime.setText(time);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void getDataFromServer() {
        ((AppSingleton) getApplicationContext())
                .getApiInterface()
                .userGame(getIntent().getStringExtra(USER_ID))
                .enqueue(new Callback<UserDetails>() {
                    @Override
                    public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                        if (response.isSuccessful()) {
                            setDataToAdapter(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetails> call, Throwable t) {
                        Toast.makeText(EditUser.this, "failed to get data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void setDataToAdapter(UserDetails userDetails) {
        userDetailData = new ArrayList<>();
        userDetailData.addAll(userDetails.getUserDetailData());
        adapterEditUser = new AdapterEditUser(userDetails.getUserDetailData(), this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return true;
            }
        };
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterEditUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_user, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_apply) {
            showEditUserConformationDialog();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            showEditWarning();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        showEditWarning();
    }

    private void showEditWarning() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Warning");
        alertDialog.setMessage("Do you want to leave without confirming the user edit?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                "Edit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showEditUserConformationDialog();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,
                "Leave",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        alertDialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void showEditUserConformationDialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Edit User");
        alertDialog.setMessage("Do you want to confirm the user edit?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        editUser();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        alertDialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void editUser() {
        if (!NetworkUtil.isConnected(this)) {
            Toast.makeText(this, "Please connect to internet", Toast.LENGTH_SHORT).show();
            return;
        }
        com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.EditUser editUser
                = new com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.EditUser();
        editUser.setUserId(getIntent().getStringExtra(USER_ID));
        editUser.setUserDetailData(adapterEditUser.getUserDetailData());
        ((AppSingleton) getApplicationContext())
                .getApiInterface()
                .editUser(editUser, getIntent().getStringExtra(USER_ID))
                .enqueue(new Callback<EditUserResponse>() {
                    @Override
                    public void onResponse(Call<EditUserResponse> call, Response<EditUserResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equals(EditUserResponse.Response.SUCCESS.getCode())) {
                                Toast.makeText(EditUser.this, "User Edited", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } else {
                            Toast.makeText(EditUser.this, "failed to edit user", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EditUserResponse> call, Throwable t) {
                        Toast.makeText(EditUser.this, "error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
