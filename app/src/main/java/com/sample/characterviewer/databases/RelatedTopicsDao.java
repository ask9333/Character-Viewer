package com.sample.characterviewer.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RelatedTopicsDao {

    @Insert
    void addRelatedTopics(DbRelatedTopics related_topics);

    @Query("select * from related_topics")
    List<DbRelatedTopics> getAllTopics();

    @Query("select character_name from related_topics where character_name like '%'||:name||'%'")
    List<String> getSearchedNames(String name);

    @Query("select iconsurl from related_topics where character_name like '%'||:name||'%'")
    List<String> getSearchedUrls(String name);

    @Query("select character_name from related_topics")
    List<String> getAllNames();

    @Query("select iconsurl from related_topics")
    List<String> getIconUrls();

    @Query("select * from related_topics where character_name like :name")
    DbRelatedTopics getSelectedTopic(String name);

    @Update
    void deleteRelatedTopic(DbRelatedTopics related_topics);

    @Delete
    void updateRelatedTopic(DbRelatedTopics related_topics);
}
