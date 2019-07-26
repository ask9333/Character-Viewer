package com.sample.characterviewer.databases;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "RELATED_TOPICS")
public class DbRelatedTopics {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "character_description")
    private String characterDescription;
    @ColumnInfo(name = "character_name")
    private String characterName;
    @Embedded(prefix = "icons")
    private DbIconDetails iconDetails;
    @ColumnInfo(name = "firstURL")
    private String firstURL;

    public DbRelatedTopics(int id, String characterDescription, String characterName, DbIconDetails iconDetails, String firstURL) {
        this.id = id;
        this.characterDescription = characterDescription;
        this.characterName = characterName;
        this.iconDetails = iconDetails;
        this.firstURL = firstURL;
    }

    @Ignore
    public DbRelatedTopics() {
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }

    public DbIconDetails getIconDetails() {
        return iconDetails;
    }

    public void setIconDetails(DbIconDetails iconDetails) {
        this.iconDetails = iconDetails;
    }

    public String getFirstURL() {
        return firstURL;
    }

    public void setFirstURL(String firstURL) {
        this.firstURL = firstURL;
    }

    @Override
    public String toString() {
        return "Saved Item:" +
                "id= " + id +
                ", Name= " + characterName +
                ", URL= " + iconDetails.getUrl() +
                ", firstURL= " + firstURL +
                ", Description= " + characterDescription;
    }
}
