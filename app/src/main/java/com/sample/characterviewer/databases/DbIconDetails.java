package com.sample.characterviewer.databases;


import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


public class DbIconDetails {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "width")
    private String width;
    @ColumnInfo(name = "height")
    private String height;

    @Ignore
    public DbIconDetails() {
    }

    public DbIconDetails(int id, String url, String width, String height) {
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
