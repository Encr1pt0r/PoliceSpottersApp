package com.Encr1pt0r.kevin_police_spotters.model;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    // inspired by findings at https://developer.android.com/training/data-storage/room/referencing-data#java
    // to store dates into the database
}
