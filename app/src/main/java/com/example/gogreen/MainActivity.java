package com.example.gogreen;

import android.os.Bundle;
import com.example.gogreen.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home); // ✅ Correct layout that contains frameLayout

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // ✅ Load HomeFragment by default
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int id = item.getItemId();
            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_pickups) {
                selectedFragment = new MyRequestsFragment();
            } else if (id == R.id.nav_subscription) {
                selectedFragment = new RewardsFragment();
            } else if (id == R.id.nav_profile) {
                selectedFragment = new ProfileFragment(); // or OrderFragment
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()

                        .commit();
            }

            return true;
        });
    }

        private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .commit();
    }
}
