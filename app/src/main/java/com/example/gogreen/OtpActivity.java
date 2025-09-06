package com.example.gogreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtpActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5, et6;
    Button verifyButton;
    String mobileNumber;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Get mobile number from intent
        mobileNumber = getIntent().getStringExtra("mobile");

        et1 = findViewById(R.id.otp_digit1);
        et2 = findViewById(R.id.otp_digit2);
        et3 = findViewById(R.id.otp_digit3);
        et4 = findViewById(R.id.otp_digit4);
        et5 = findViewById(R.id.otp_digit5);
        et6 = findViewById(R.id.otp_digit6);
        verifyButton = findViewById(R.id.verify_button);

        setupOtpInputs();

        verifyButton.setOnClickListener(v -> {
            String otp = et1.getText().toString() +
                    et2.getText().toString() +
                    et3.getText().toString() +
                    et4.getText().toString() +
                    et5.getText().toString() +
                    et6.getText().toString();

            if (otp.length() == 6) {
                // Normally verify with Firebase here
                Toast.makeText(this, "OTP Entered: " + otp, Toast.LENGTH_SHORT).show();

                // Proceed to next screen
                Intent intent = new Intent(OtpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Please enter all 6 digits", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupOtpInputs() {
        EditText[] editTexts = {et1, et2, et3, et4, et5, et6};

        for (int i = 0; i < editTexts.length; i++) {
            final int current = i;
            editTexts[i].addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 1 && current < editTexts.length - 1) {
                        editTexts[current + 1].requestFocus();
                    } else if (s.length() == 0 && current > 0) {
                        editTexts[current - 1].requestFocus();
                    }
                }
            });
        }
    }
}
