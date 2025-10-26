package com.example.gogreen;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BagPagerAdapter extends FragmentStateAdapter {

    public BagPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return BagFragment.newInstance("Small Bag", "Capacity: 5kg | Material: Biodegradable", "₹20");
            case 1:
                return BagFragment.newInstance("Medium Bag", "Capacity: 10kg | Material: Biodegradable", "₹40");
            case 2:
                return BagFragment.newInstance("Large Bag", "Capacity: 20kg | Material: Biodegradable", "₹70");
            default:
                return BagFragment.newInstance("Small Bag", "Capacity: 5kg", "₹20");
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Small, Medium, Large
    }
}
