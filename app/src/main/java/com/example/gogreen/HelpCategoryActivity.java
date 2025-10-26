package com.example.gogreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HelpCategoryActivity extends AppCompatActivity {

    private ListView helpListView;
    private List<HelpItem> questionList;
    private String categoryTitle;
TextView supportText;
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_category);
        supportText=findViewById(R.id.supportText);

        helpListView = findViewById(R.id.questionListView);
        TextView categoryTitleTextView = findViewById(R.id.categoryTitle);
        supportText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HelpCategoryActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });
        // ✅ Get the category name from Intent (before setting the title)
        categoryTitle = getIntent().getStringExtra("category_name");

        // ✅ Debugging (Check if categoryTitle is received)
        System.out.println("Received category: " + categoryTitle);

        // ✅ Set the title dynamically (only if it's not null)
        if (categoryTitle != null) {
            categoryTitleTextView.setText(categoryTitle);
        } else {
            categoryTitleTextView.setText("Category Not Found"); // Default fallback
        }

        // ✅ Populate questions based on category
        questionList = getQuestionsForCategory(categoryTitle);

        // ✅ Convert HelpItem objects to question strings for ListView
        List<String> questionTitles = new ArrayList<>();
        for (HelpItem item : questionList) {
            questionTitles.add(item.getTitle());
        }

        // ✅ Set up ListView with an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_question, R.id.questionTitle, questionTitles);
        helpListView.setAdapter(adapter);


        // ✅ Handle item clicks (Opens HelpDetailActivity)
        helpListView.setOnItemClickListener((parent, view, position, id) -> {
            HelpItem selectedItem = questionList.get(position);
            Intent intent = new Intent(HelpCategoryActivity.this, HelpDetailActivity.class);
            intent.putExtra("category_name", categoryTitle);
            intent.putExtra("question", selectedItem.getTitle());
            intent.putExtra("answer", selectedItem.getDescription());

            // ✅ Debugging (Check if answer is passed correctly)
            System.out.println("Passing answer: " + selectedItem.getDescription());

            startActivity(intent);
        });

        // ✅ Back button functionality
        findViewById(R.id.backButton).setOnClickListener(v -> finish());
    }

    private List<HelpItem> getQuestionsForCategory(String category) {
        List<HelpItem> list = new ArrayList<>();

        if (category.equals("Waste Collection")) {
            list.add(new HelpItem("How does TrashMate collect my waste?",
                    "TrashMate provides doorstep waste pickup. Once you schedule, our partner comes to your location on the chosen day and time.",
                    R.drawable.help));

            list.add(new HelpItem("Can I schedule waste pickup multiple times a week?",
                    "Yes, you can schedule pickups 2, 3, or 4 times a week depending on your plan.",
                    R.drawable.help));

            list.add(new HelpItem("What types of waste can I give?",
                    "We collect both dry and wet waste separately. Please ensure proper segregation before handing over.",
                    R.drawable.help));

            list.add(new HelpItem("Is doorstep collection available for businesses?",
                    "Yes, we offer customized collection plans for households, apartments, and businesses.",
                    R.drawable.help));

            list.add(new HelpItem("What if I miss my scheduled pickup?",
                    "If you miss a pickup, you can easily reschedule from the 'My Requests' section in the app.",
                    R.drawable.help));
        }

        else if (category.equals("Bag Orders & Payments")) {
            list.add(new HelpItem("How can I buy TrashMate bags?",
                    "You can purchase verified TrashMate QR-coded bags directly from the app under the 'Buy Bags' section.",
                    R.drawable.help));

            list.add(new HelpItem("What is the payment method for bags?",
                    "We accept UPI, PhonePe, Google Pay, debit/credit cards, and net banking.",
                    R.drawable.help));

            list.add(new HelpItem("Can I track my bag order?",
                    "Yes, you can track your order status in the 'My Orders' section of the app.",
                    R.drawable.help));

            list.add(new HelpItem("Do I need TrashMate bags for waste pickup?",
                    "Yes, only TrashMate QR-coded bags are accepted to ensure verified collection.",
                    R.drawable.help));
        }

        else if (category.equals("Pickup Scheduling")) {
            list.add(new HelpItem("How do I schedule a waste pickup?",
                    "Go to the 'Schedule Pickup' section, select your address, choose pickup days, and confirm.",
                    R.drawable.help));

            list.add(new HelpItem("Can I change my pickup time?",
                    "Yes, you can reschedule or edit pickup time anytime before the scheduled slot.",
                    R.drawable.help));

            list.add(new HelpItem("Is there an option for emergency pickup?",
                    "Yes, TrashMate offers on-demand emergency pickup at an additional cost.",
                    R.drawable.help));

            list.add(new HelpItem("How do I track my pickup partner?",
                    "Once assigned, you can track your pickup partner in real-time from the 'My Requests' tab.",
                    R.drawable.help));
        }

        else if (category.equals("Profile & Address Updates")) {
            list.add(new HelpItem("How can I update my profile details?",
                    "Go to the Profile section, where you can edit your name, phone number, and email address.",
                    R.drawable.help));

            list.add(new HelpItem("Can I add multiple addresses?",
                    "Yes, you can add multiple addresses such as home, office, or apartment, and choose while scheduling pickup.",
                    R.drawable.help));

            list.add(new HelpItem("Is my data secure?",
                    "Yes, TrashMate ensures complete privacy with encrypted data and secure authentication.",
                    R.drawable.help));
        }

        else if (category.equals("General Issues")) {
            list.add(new HelpItem("How to contact TrashMate support?",
                    "Go to the 'Help & Support' section in the app to call or chat with our support team.",
                    R.drawable.help));

            list.add(new HelpItem("Why am I not receiving notifications?",
                    "Check your app and device notification settings to make sure alerts are enabled.",
                    R.drawable.help));

            list.add(new HelpItem("How to report a missed pickup?",
                    "If your waste was not collected, you can report it under 'My Requests' or contact support.",
                    R.drawable.help));

            list.add(new HelpItem("How to report a bug or app issue?",
                    "Use the 'Feedback & Support' section to submit issues directly to our team.",
                    R.drawable.help));
        }

        return list;
    }


}
