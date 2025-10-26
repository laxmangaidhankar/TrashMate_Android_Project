package com.example.gogreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editTextPhone;
    private Button continueButton;
    private static final int RC_SIGN_IN = 1001;
    private GoogleSignInClient mGoogleSignInClient;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        editTextPhone = findViewById(R.id.edittext1);
        continueButton = findViewById(R.id.button1);
        TextView tryFirst = findViewById(R.id.try_first_text);

        // Handle Continue button click
        continueButton.setOnClickListener(v -> {
            String phone = editTextPhone.getText().toString().trim();

            if (phone.isEmpty()) {
                editTextPhone.setError("Phone number required");
                return;
            }

            if (phone.length() != 10 || !phone.matches("\\d{10}")) {
                editTextPhone.setError("Enter a valid 10-digit phone number");
                return;
            }

            // ✅ If valid, go to OTP screen directly
            String fullPhoneNumber = "+91" + phone;
            Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
            intent.putExtra("mobile", fullPhoneNumber);
            startActivity(intent);
        });

        // "Try First" → skip login
        tryFirst.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        });

        // Configure Google Sign-In (optional, not used here)
        configureGoogleSignIn();
    }

    // Configure Google Sign-In
    private void configureGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this, gso);
    }
}
