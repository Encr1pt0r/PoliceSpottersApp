package com.Encr1pt0r.kevin_police_spotters.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {SpotCheck.class}, version = 1)
@TypeConverters({DateConverters.class}) // to link dateTime element to the database
public abstract class SpotCheckDB extends RoomDatabase {
    public abstract SpotCheckDao spotCheckDao();

    private static SpotCheckDB INSTANCE;

    public static SpotCheckDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SpotCheckDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SpotCheckDB.class, "phoneBook_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
