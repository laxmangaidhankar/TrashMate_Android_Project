package com.example.gogreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TermsActivity extends AppCompatActivity {

    CheckBox checkboxAccept;
    Button btnContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        checkboxAccept = findViewById(R.id.checkboxAccept);
        btnContinue = findViewById(R.id.btnContinue);

        // Back button action

        // Continue button action
        btnContinue.setOnClickListener(v -> {
            if (checkboxAccept.isChecked()) {
                // ✅ User accepted Terms, go to ProfileActivity
                Intent intent = new Intent(TermsActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish(); // Optional: close TermsActivity
            } else {
                // ❌ User did not accept
                Toast.makeText(this, "Please accept the Terms & Conditions first.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
