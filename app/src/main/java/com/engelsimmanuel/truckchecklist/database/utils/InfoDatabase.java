package com.engelsimmanuel.truckchecklist.database.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Info.class}, version = 1, exportSchema = false)
public abstract class InfoDatabase extends RoomDatabase {

    private static InfoDatabase instance;

    public abstract InfoDao infoDao();

    public static synchronized InfoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    InfoDatabase.class,
                    "info_database"
            )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
