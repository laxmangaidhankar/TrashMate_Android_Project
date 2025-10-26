package com.example.gogreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class splash extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000; // 3 seconds
    private static final String PREFS_NAME = "UserSession"; // SharedPreferences file name
    private static final String KEY_IS_LOGGED_IN = "is_logged_in"; // Key to check if user is logged in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(() -> {
            // Check login status using SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            boolean isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);

            if (isLoggedIn) {
                // User is logged in, redirect to DashboardActivity
                startActivity(new Intent(splash.this, HomeActivity.class));
            } else {
                // No user logged in, show LoginActivity
                startActivity(new Intent(splash.this, LoginActivity.class));
            }
            finish(); // Close splash screen
        }, SPLASH_TIME);
    }
}
