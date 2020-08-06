package com.example.sqlitepractice;

public class SqlItem {
    private String title;
    private String description;

    public SqlItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
