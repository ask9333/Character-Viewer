package com.sample.characterviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterInfo {

    @SerializedName("RelatedTopics")
    @Expose
    private List<RelatedTopics> relatedTopics;

    public List<RelatedTopics> getRelatedTopics() {
        return relatedTopics;
    }

    public void setRelatedTopics(List<RelatedTopics> relatedTopics) {
        this.relatedTopics = relatedTopics;
    }
}

