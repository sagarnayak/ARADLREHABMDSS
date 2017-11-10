package com.sagar.android_projects.ar_adl_rehab_mdss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sagar.android_projects.ar_adl_rehab_mdss.util.Keyword;

public class Login extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = findViewById(R.id.edittext_username_login);
        editTextPassword = findViewById(R.id.edittext_password_login);
        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDashBoard();
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
