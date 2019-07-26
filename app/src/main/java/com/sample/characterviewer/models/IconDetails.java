package com.sample.characterviewer.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IconDetails {

    @SerializedName("URL")
    @Expose
    private String url;

    @SerializedName("Height")
    @Expose
    private String height;

    @SerializedName("Width")
    @Expose
    private String width;

    public String getURL() {
        return url;
    }

    public void setURL(String uRL) {
        this.url = uRL;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

}
