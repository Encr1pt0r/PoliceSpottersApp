package com.Encr1pt0r.kevin_police_spotters.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface SpotCheckDao {

    @Insert
    void insert(SpotCheck spotCheck);

    @Delete
    void deleteSpotChecks(SpotCheck... spotChecks);

    @Update
    void updateSpotChecks(SpotCheck... spotChecks);

    @Query("SELECT * from SpotCheck ORDER BY dateTime ASC")
    LiveData<List<SpotCheck>> getAllSpotChecks();

    // Method designated to finding between two dates
    @Query("SELECT * FROM SpotCheck WHERE dateTime BETWEEN :from AND :to")
    LiveData<List<SpotCheck>> findChecksBetweenDates(LocalDateTime from, LocalDateTime to);
}
