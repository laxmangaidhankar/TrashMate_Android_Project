package com.example.gogreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements PaymentResultListener {

    private RecyclerView recyclerViewFeedback;
    private FeedbackAdapter feedbackAdapter;
    private ArrayList<Feedback> feedbackList;
    private LinearLayout subscriptionbtn;

    private RelativeLayout cardUpcoming;
    private LinearLayout layoutScheduleForm;
    private ImageView arrowIconPickup;

    private RadioButton rbAddress, rbManualAddress, rbGeneral, rbOrganic;
    private TextInputEditText etManualAddress, etDate, etTime;
    private TextView tvQuantityGeneral, tvTotalGeneral, tvQuantityOrganic, tvTotalOrganic, tvTotalPrice;
    private MaterialButton btnSchedulePickup;

    private int generalQty = 1, organicQty = 0;
    private int priceGeneral = 19, priceOrganic = 29;
    private int totalAmount = 0;

    private String userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        // 🔹 Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return false;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_pickups) {
                startActivity(new Intent(HomeActivity.this, ActivityMyPickups.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_store) {
                startActivity(new Intent(HomeActivity.this, BagActivity.class));
                finish();
                return true;
            }
            return false;
        });





        // 🔹 Bind Views
        rbAddress = findViewById(R.id.rb_address);
        rbManualAddress = findViewById(R.id.rb_manual_address);
        etManualAddress = findViewById(R.id.et_manual_address);
        etDate = findViewById(R.id.et_date);
        etTime = findViewById(R.id.et_time);

        rbGeneral = findViewById(R.id.rb_general);
        rbOrganic = findViewById(R.id.rb_organic);

        tvQuantityGeneral = findViewById(R.id.tv_quantity_general);
        tvTotalGeneral = findViewById(R.id.tv_total_general);
        tvQuantityOrganic = findViewById(R.id.tv_quantity3_general);
        tvTotalOrganic = findViewById(R.id.tv_total3_general);
        tvTotalPrice = findViewById(R.id.tv_total_price);

        btnSchedulePickup = findViewById(R.id.btn_schedule_pickup);

        // 🔹 Quantity buttons
        findViewById(R.id.btn_plus_general).setOnClickListener(v -> updateGeneralQty(1));
        findViewById(R.id.btn_minus_general).setOnClickListener(v -> updateGeneralQty(-1));
        findViewById(R.id.btn_plus3_general).setOnClickListener(v -> updateOrganicQty(1));
        findViewById(R.id.btn_minus3_general).setOnClickListener(v -> updateOrganicQty(-1));

        rbManualAddress.setOnCheckedChangeListener((compoundButton, checked) -> {
            etManualAddress.setEnabled(checked);
        });

        // 🔹 Proceed to pay
        btnSchedulePickup.setOnClickListener(v -> {
            calculateTotal();
            startPayment();
        });

        // 🔹 Setup Subscription button
        subscriptionbtn = findViewById(R.id.subscriptionbtn);
        subscriptionbtn.setOnClickListener(v -> {
            startActivity(new Intent(this, SubscriptionActivity.class));
        });

        // 🔹 Setup Feedback RecyclerView
        recyclerViewFeedback = findViewById(R.id.recyclerViewFeedback);
        recyclerViewFeedback.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        feedbackList = new ArrayList<>();
        feedbackList.add(new Feedback("Best app for Waste Collection",
                "Booking a pickup is so simple, the waste collector came right on time from my doorstep.",
                "Mohini Patil", R.drawable.fema));
        feedbackList.add(new Feedback("Bag Purchase Feature is Useful",
                "I easily bought the waste collection bags directly from the app. The QR code system is really smooth.",
                "Anushka Mule", R.drawable.fema));
        feedbackList.add(new Feedback("Profile Section is Handy",
                "I added my details and now I can track my waste requests and bag purchases all in one place.",
                "Riya Rathod", R.drawable.fema));
        feedbackList.add(new Feedback("Navigation is Smooth",
                "The app design is clean and moving from one feature to another like Pickup, Profile, and Requests is seamless.",
                "Yash Pawar", R.drawable.male));
        feedbackList.add(new Feedback("Home Pickup Service is Reliable",
                "I scheduled a home waste pickup and within hours the collector arrived.",
                "Aditya Kumbhar", R.drawable.male));
        feedbackList.add(new Feedback("Nearby Centers Helped Me",
                "The feature to find nearby waste centers is very useful. I found a recycling point just near my colony.",
                "Sunil Sharma", R.drawable.male));
        feedbackList.add(new Feedback("Support Team is Quick",
                "I raised a support request regarding bag verification, and it was solved within a day. Very responsive!",
                "Latika Jha", R.drawable.fema));

        feedbackAdapter = new FeedbackAdapter(feedbackList);
        recyclerViewFeedback.setAdapter(feedbackAdapter);

        // 🔹 Expand/Collapse Schedule Pickup
        cardUpcoming = findViewById(R.id.cardUpcoming);
        layoutScheduleForm = findViewById(R.id.layoutScheduleForm);
        arrowIconPickup = findViewById(R.id.arrowIconPickup);

        cardUpcoming.setOnClickListener(v -> toggleLayout());
    }

    private void updateGeneralQty(int change) {
        generalQty = Math.max(0, generalQty + change);
        tvQuantityGeneral.setText(String.valueOf(generalQty));
        tvTotalGeneral.setText("Total: ₹" + (generalQty * priceGeneral));
        calculateTotal();
    }

    private void updateOrganicQty(int change) {
        organicQty = Math.max(0, organicQty + change);
        tvQuantityOrganic.setText(String.valueOf(organicQty));
        tvTotalOrganic.setText("Total: ₹" + (organicQty * priceOrganic));
        calculateTotal();
    }

    private void calculateTotal() {
        totalAmount = (generalQty * priceGeneral) + (organicQty * priceOrganic);
        tvTotalPrice.setText("₹" + totalAmount);
    }

    // 🔹 Razorpay Payment
    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_yourapikey"); // Replace with your Razorpay Key ID

        try {
            JSONObject options = new JSONObject();
            options.put("name", "GoGreen Waste Pickup");
            options.put("description", "Pickup Payment");
            options.put("currency", "INR");
            options.put("amount", totalAmount * 100); // paise

            checkout.open(this, options);
        } catch (Exception e) {
            Toast.makeText(this, "Payment error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // 🔹 Handle Payment Success
    @Override
    public void onPaymentSuccess(String razorpayPaymentId) {
        savePickupDetails("Paid", razorpayPaymentId);
        Toast.makeText(this, "Payment Success! Pickup Scheduled", Toast.LENGTH_LONG).show();

        startActivity(new Intent(HomeActivity.this, ActivityMyPickups.class));
        finish();
    }

    // 🔹 Handle Payment Failure
    @Override
    public void onPaymentError(int code, String response) {
        savePickupDetails("Failed", "");
        Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_LONG).show();
    }

    // 🔹 Save Pickup to Firestore
    private void savePickupDetails(String status, String paymentId) {
        String pickupAddress = rbManualAddress.isChecked()
                ? etManualAddress.getText().toString()
                : "123 Green Street, Pune, Maharashtra - 411001";

        String wasteType = rbGeneral.isChecked() ? "General Waste" : "Organic Waste";

        Map<String, Object> pickup = new HashMap<>();
        pickup.put("userId", userId);
        pickup.put("pickupAddress", pickupAddress);
        pickup.put("wasteType", wasteType);
        pickup.put("generalQty", generalQty);
        pickup.put("organicQty", organicQty);
        pickup.put("totalAmount", totalAmount);
        pickup.put("date", etDate.getText().toString());
        pickup.put("time", etTime.getText().toString());
        pickup.put("paymentStatus", status);
        pickup.put("paymentId", paymentId);
        pickup.put("scheduleStatus", status.equals("Paid") ? "Scheduled" : "Not Scheduled");


    }

    // Expand/Collapse Animations
    private void toggleLayout() {
        if (layoutScheduleForm.getVisibility() == View.GONE) {
            expand(layoutScheduleForm);
            arrowIconPickup.animate().rotation(90).setDuration(300).start();
        } else {
            collapse(layoutScheduleForm);
            arrowIconPickup.animate().rotation(0).setDuration(300).start();
        }
    }

    public static void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height =
                        interpolatedTime == 1
                                ? LinearLayout.LayoutParams.WRAP_CONTENT
                                : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() { return true; }
        };

        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density) * 4);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() { return true; }
        };

        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density) * 4);
        v.startAnimation(a);
    }
}
