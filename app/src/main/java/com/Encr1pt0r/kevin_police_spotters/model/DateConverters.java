package com.Encr1pt0r.kevin_police_spotters.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.util.Date;

public class DateConverters {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDateTime toDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            return LocalDateTime.parse(dateString);
        }
    }

    @TypeConverter
    public static String toDateString(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }

    // inspired by findings at https://developer.android.com/training/data-storage/room/referencing-data#java
    // to store dates into the database

    // Had to change for new LocalDateTime storage, idea is to store as String in SQLite
    // came from https://stackoverflow.com/questions/54927913/room-localdatetime-typeconverter
    // only downside is that it can only run on new devices
}
