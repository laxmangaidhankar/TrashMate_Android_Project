package com.example.gogreen;

public class HelpItem {
    private String title;
    private String description;  // Ensure this field exists
    private int icon;

    public HelpItem(String title, String description, int icon) {
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { // Ensure this method exists
        return description;
    }

    public int getIcon() {
        return icon;
    }
}

