package com.example.gogreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpDetailActivity extends AppCompatActivity {

    private TextView categoryTitleText, questionText, answerText,supportText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);

        // Initialize UI elements
        categoryTitleText = findViewById(R.id.categoryTitle);
        questionText = findViewById(R.id.questionText);
        answerText = findViewById(R.id.answerText);
        supportText=findViewById(R.id.supportText);
        ImageView backButton = findViewById(R.id.backButton);
        supportText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HelpDetailActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });
        // Get data from Intent
        String category = getIntent().getStringExtra("category_name");
        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");

        // Log values for debugging
        Log.d("HelpDetailActivity", "Category: " + category);
        Log.d("HelpDetailActivity", "Question: " + question);
        Log.d("HelpDetailActivity", "Answer: " + answer);  // Check if the answer is retrieved

        // Set the values dynamically
        if (category != null) categoryTitleText.setText(category);
        if (question != null) questionText.setText(question);
        if (answer != null && !answer.isEmpty()) {
            answerText.setText(answer);
        } else {
            answerText.setText("No answer available."); // Set default text if null
        }

        // Back button click
        backButton.setOnClickListener(v -> finish());
    }
}
