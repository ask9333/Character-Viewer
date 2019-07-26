package com.sample.characterviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedTopics {

    @SerializedName("Result")
    @Expose
    private String result;

    @SerializedName("Text")
    @Expose
    private String text;

    @SerializedName("Icon")
    @Expose
    private IconDetails iconDetails;

    @SerializedName("FirstURL")

    private String firstURL;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public IconDetails getIconDetails() {
        return iconDetails;
    }

    public void setIconDetails(IconDetails iconDetails) {
        this.iconDetails = iconDetails;
    }

    public String getFirstURL() {
        return firstURL;
    }

    public void setFirstURL(String firstURL) {
        this.firstURL = firstURL;
    }

}
