package com.example.gogreen;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUsActivity extends Activity {

    private EditText nameInput, emailInput, phoneInput, messageInput;
    private Button sendEmailBtn, callBtn, whatsappBtn, attachFileBtn;
    private TextView supportHours;
    private static final int PICK_FILE_REQUEST = 1;
    private Uri fileUri;
ImageView backButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
backButton=findViewById(R.id.backButton);
backButton.setOnClickListener(view -> onBackPressed());
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        phoneInput = findViewById(R.id.phoneInput);
        messageInput = findViewById(R.id.messageInput);
        sendEmailBtn = findViewById(R.id.sendEmailBtn);
        callBtn = findViewById(R.id.callBtn);
        whatsappBtn = findViewById(R.id.whatsappBtn);
        attachFileBtn = findViewById(R.id.attachFileBtn);
        supportHours = findViewById(R.id.supportHours);

        // Set support hours
        supportHours.setText("Support available: Mon-Fri, 9 AM - 6 PM");

        // Attach File Button
        attachFileBtn.setOnClickListener(v -> pickFile());

        // Send Email Button
        sendEmailBtn.setOnClickListener(v -> sendEmail());

        // Call Button
        callBtn.setOnClickListener(v -> makePhoneCall("+918855013674"));

        // WhatsApp Button
        whatsappBtn.setOnClickListener(v -> openWhatsApp("+918855013674"));
    }

    // Method to open file picker
    private void pickFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }

    // Handle selected file
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            fileUri = data.getData();
            Toast.makeText(this, "File Selected: " + fileUri.getPath(), Toast.LENGTH_SHORT).show();
        }
    }

    // Method to send email
    private void sendEmail() {
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String message = messageInput.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gaidhankarl@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Medibox Support Query from " + name);
        intent.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nMessage: " + message);

        if (fileUri != null) {
            intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        }

        try {
            startActivity(Intent.createChooser(intent, "Send Email"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email clients installed!", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to make a phone call
    private void makePhoneCall(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    // Method to open WhatsApp chat
    private void openWhatsApp(String number) {
        try {
            String url = "https://api.whatsapp.com/send?phone=" + number;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "WhatsApp not installed!", Toast.LENGTH_SHORT).show();
        }
    }
}
