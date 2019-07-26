package com.sample.characterviewer.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = DbRelatedTopics.class, version = 1, exportSchema = false)
public abstract class RelatedTopicsDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "character_names";
    private static final Object LOCK = new Object();
    private static final String TAG = RelatedTopicsDatabase.class.getSimpleName();
    private static RelatedTopicsDatabase singleInstance;

    public static RelatedTopicsDatabase getInstance(Context context) {
        if (singleInstance == null) {
            synchronized (LOCK) {
                singleInstance = Room.databaseBuilder(context.getApplicationContext(),
                        RelatedTopicsDatabase.class, RelatedTopicsDatabase.DATABASE_NAME).allowMainThreadQueries().build();
            }

        }
        return singleInstance;
    }

    public abstract RelatedTopicsDao getRelatedTopicsDao();

}
