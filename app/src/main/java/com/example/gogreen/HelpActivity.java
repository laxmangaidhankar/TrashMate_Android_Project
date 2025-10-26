package com.example.gogreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends Activity {
    private ListView listView;
    private EditText searchBar;
    private ImageView backButton;
    private HelpAdapter adapter;
    private List<HelpItem> helpList;
TextView supportText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
supportText=findViewById(R.id.supportText);
        supportText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HelpActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.helpListView);
        searchBar = findViewById(R.id.searchBar);
        backButton = findViewById(R.id.backButton);

        // Initialize Help List
        helpList = new ArrayList<>();
        helpList.add(new HelpItem("Waste Collection", "8 Answers", R.drawable.ic_bell));
        helpList.add(new HelpItem("Bag Orders & Payments", "5 Answers", R.drawable.ic_bell));
        helpList.add(new HelpItem("Pickup Scheduling", "6 Answers", R.drawable.schedule));
        helpList.add(new HelpItem("Profile & Address Updates", "3 Answers", R.drawable.ic_bell));
        helpList.add(new HelpItem("General Issues", "4 Answers", R.drawable.ic_bell));

        adapter = new HelpAdapter(this, helpList);
        listView.setAdapter(adapter);

        // ✅ Add TextWatcher for Search Bar
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s); // ✅ Apply search filter
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        // ✅ Item Click Listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            HelpItem selectedItem = (HelpItem) adapter.getItem(position);
            Intent intent = new Intent(HelpActivity.this, HelpCategoryActivity.class);
            intent.putExtra("category_name", selectedItem.getTitle());
            startActivity(intent);
        });

        // ✅ Back button functionality
        backButton.setOnClickListener(v -> finish());
    }
}
