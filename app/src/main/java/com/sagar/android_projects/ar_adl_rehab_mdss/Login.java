package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login.LoginRequest;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login.LoginResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.Keyword;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    @SuppressWarnings("FieldCanBeLocal")
    private Button buttonLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = findViewById(R.id.edittext_username_login);
        editTextPassword = findViewById(R.id.edittext_password_login);
        buttonLogin = findViewById(R.id.button_login);
        progressBar = findViewById(R.id.progressbar_patient_details);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtil.isConnected(Login.this)) {
                    Toast.makeText(Login.this, "Connect to internet", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editTextUserName.getText().length() == 0) {
                    Toast.makeText(Login.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editTextPassword.getText().length() == 0) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                login(editTextUserName.getText().toString().trim(),
                        editTextPassword.getText().toString().trim());
            }
        });
    }

    private void login(String userName, String password) {
        ((AppSingleton) getApplicationContext()).getApiInterface()
                .login(new LoginRequest(userName, password))
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equals(LoginResponse.Response.SUCCESS.getCode())) {
                                loginSuccessFull();
                                gotoDashBoard();
                            } else if (response.body().getStatus().equals(LoginResponse.Response.FAIL.getCode())) {
                                Toast.makeText(Login.this, String.valueOf(response.body().getMessage()),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(Login.this, "Login failed. please try again later", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLoggedIn())
            gotoDashBoard();
    }

    private void gotoDashBoard() {
        startActivity(new Intent(Login.this, Dashboard.class));
        finish();
    }

    private void loginSuccessFull() {
        getSharedPreferences(Keyword.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
                .edit()
                .putBoolean(Keyword.IS_LOGGED_IN, true)
                .apply();
    }

    private boolean isLoggedIn() {
        try {
            if (getSharedPreferences(Keyword.SHARED_PREFERENCE_NAME, MODE_PRIVATE).getBoolean(Keyword.IS_LOGGED_IN, false))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
