package com.example.gogreen;

public class Feedback {
    private String title, message, userName;
    private int userImage;

    public Feedback(String title, String message, String userName, int userImage) {
        this.title = title;
        this.message = message;
        this.userName = userName;
        this.userImage = userImage;
    }

    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public String getUserName() { return userName; }
    public int getUserImage() { return userImage; }
}
