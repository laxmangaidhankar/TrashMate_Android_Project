package com.example.gogreen;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BagActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_plastic_bags);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Adapter
        BagPagerAdapter adapter = new BagPagerAdapter(this);
        viewPager.setAdapter(adapter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_store);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(BagActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(BagActivity.this, ProfileActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_pickups) {
                startActivity(new Intent(BagActivity.this, ActivityMyPickups.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_store) {

                return false;
            }
            return false;
        });
        // Attach tabs
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Small");
                    break;
                case 1:
                    tab.setText("Medium");
                    break;
                case 2:
                    tab.setText("Large");
                    break;
            }
        }).attach();
    }
}
