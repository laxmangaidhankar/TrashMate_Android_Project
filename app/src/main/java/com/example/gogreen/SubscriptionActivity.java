package com.example.gogreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class SubscriptionActivity extends AppCompatActivity {

    ImageView backButton;
    TabLayout tabLayout;
    ScrollView residentialScroll, businessScroll;

    // Residential buttons
    Button btnBasicSubscribe, btnStandardSubscribe, btnPremiumSubscribe;

    // Business buttons
    Button btnBusinessBasic, btnBusinessStandard, btnBusinessPremium;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription); // XML file

        // --- Views ---
        backButton = findViewById(R.id.backButton);
        tabLayout = findViewById(R.id.tabLayout);
        residentialScroll = findViewById(R.id.residentialScroll);
        businessScroll = findViewById(R.id.businessScroll);

        // Residential buttons
        btnBasicSubscribe = findViewById(R.id.btnBasicSubscribe);
        btnStandardSubscribe = findViewById(R.id.btnStandardSubscribe);
        btnPremiumSubscribe = findViewById(R.id.btnPremiumSubscribe);

        // Business buttons
        btnBusinessBasic = findViewById(R.id.btnBussinessBasic);
        btnBusinessStandard = findViewById(R.id.btnBusinessStandard);
        btnBusinessPremium = findViewById(R.id.btnBusinessPremium);

        // --- Back button click ---
        backButton.setOnClickListener(v -> onBackPressed());

        // --- TabLayout listener ---
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) { // Residential
                    residentialScroll.setVisibility(View.VISIBLE);
                    businessScroll.setVisibility(View.GONE);
                } else { // Business
                    residentialScroll.setVisibility(View.GONE);
                    businessScroll.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // --- Residential subscriptions ---
        btnBasicSubscribe.setOnClickListener(v -> showToast("Subscribed to Residential Basic Plan!"));
        btnStandardSubscribe.setOnClickListener(v -> showToast("Subscribed to Residential Standard Plan!"));
        btnPremiumSubscribe.setOnClickListener(v -> showToast("Subscribed to Residential Premium Plan!"));

        // --- Business subscriptions ---
        btnBusinessBasic.setOnClickListener(v -> showToast("Subscribed to Business Basic Plan!"));
        btnBusinessStandard.setOnClickListener(v -> showToast("Subscribed to Business Standard Plan!"));
        btnBusinessPremium.setOnClickListener(v -> showToast("Subscribed to Business Premium Plan!"));
    }

    // Helper function for showing Toast
    private void showToast(String message) {
        Toast.makeText(SubscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
