package com.example.gogreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private Button btnLoginRegister, btnUpdateDetails;
    CardView managesubscriptin,helpandsupport;
    private TextView btn_logout, terms, textname, terms_conditions;
    private RelativeLayout profile_section_relative;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_MOBILE = "mobile_number";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        managesubscriptin = findViewById(R.id.managesubscriptin);
        managesubscriptin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SubscriptionActivity.class);
                startActivity(intent);
            }
        });
        helpandsupport=findViewById(R.id.helpandsupport);
        helpandsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
        terms_conditions = findViewById(R.id.terms_conditions);
        terms_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, TermsActivity.class);
                startActivity(intent);
            }
        });
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Initialize UI Components
        terms = findViewById(R.id.terms_conditions);
        btnLoginRegister = findViewById(R.id.btn_login_register);
        btnUpdateDetails = findViewById(R.id.btn_update_profile);
        btn_logout = findViewById(R.id.btn_logout);


        // Terms and Conditions Click Event
        terms.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, TermsActivity.class)));

        // Bottom Navigation Setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                return false;
            } else if (itemId == R.id.nav_store) {
                startActivity(new Intent(ProfileActivity.this, BagActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_pickups) {
                startActivity(new Intent(ProfileActivity.this, ActivityMyPickups.class));
                finish();
                return true;
            }
            return false;
        });


        // Handle login/register button click


    }
}
