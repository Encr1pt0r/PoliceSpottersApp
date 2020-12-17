package com.Encr1pt0r.kevin_police_spotters.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class SpotCheck {
    //Each element in a spot check defined as attributes
    @PrimaryKey
    @NonNull
    private LocalDateTime dateTime;

    @NonNull
    private String location;

    @NonNull
    private String carRegNo;

    @NonNull
    private String makeOfCar;

    @NonNull
    private String result;

    @NonNull
    private String notes;

    // Rest of the stuff
    public SpotCheck(@NonNull LocalDateTime dateTime, @NonNull String location, @NonNull String carRegNo,
                     @NonNull String makeOfCar, @NonNull String result, @NonNull String notes) {
        this.dateTime = dateTime;
        this.location = location;
        this.carRegNo = carRegNo;
        this.makeOfCar = makeOfCar;
        this.result = result;
        this.notes = notes;
    }

    public LocalDateTime getDateTime() { return dateTime; }

    public String getLocation() {
        return location;
    }

    public String getCarRegNo() {
        return carRegNo;
    }

    public String getMakeOfCar() {
        return makeOfCar;
    }

    public String getResult() {
        return result;
    }

    public String getNotes() {
        return notes;
    }
}
