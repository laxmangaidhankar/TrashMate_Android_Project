package com.example.gogreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ActivityMyPickups extends AppCompatActivity {

    private RecyclerView recyclerViewPickups;
    private PickupAdapter pickupAdapter;
    private List<Pickup> pickupList;

    private String userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pickups);

        recyclerViewPickups = findViewById(R.id.recyclerViewPickups);
        recyclerViewPickups.setLayoutManager(new LinearLayoutManager(this));
        pickupList = new ArrayList<>();
        pickupAdapter = new PickupAdapter(pickupList);
        recyclerViewPickups.setAdapter(pickupAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_pickups);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(ActivityMyPickups.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(ActivityMyPickups.this, ProfileActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_pickups) {

                return false;
            } else if (itemId == R.id.nav_store) {
                startActivity(new Intent(ActivityMyPickups.this, BagActivity.class));
                finish();
                return true;
            }
            return false;
        });


    }


}
