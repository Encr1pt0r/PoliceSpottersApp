package com.Encr1pt0r.kevin_police_spotters.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface SpotCheckDao {

    @Insert
    void Insert(SpotCheck spotCheck);

    @Query("SELECT * from SpotCheck ORDER BY dateTime ASC")
    LiveData<List<SpotCheck>> getAllSpotChecks();

    // Method designated to finding between two dates
    @Query("SELECT * FROM SpotCheck WHERE dateTime BETWEEN :from AND :to")
    LiveData<List<SpotCheck>> findChecksBetweenDates(Date from, Date to);
}
