package com.engelsimmanuel.truckchecklist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Info.class}, version = 1)
public abstract class InfoDatabase extends RoomDatabase {

    private static InfoDatabase instance;

    public abstract InfoDao infoDao();

    public static synchronized InfoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    InfoDatabase.class,
                    "info_database"
            ).build();
        }
        return instance;
    }
}
