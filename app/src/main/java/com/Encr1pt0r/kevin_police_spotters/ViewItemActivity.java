package com.Encr1pt0r.kevin_police_spotters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ViewItemActivity extends AppCompatActivity {

    private LocalDateTime dateTime;
    private String location;
    private String carReg;
    private String carModel;
    private String result;
    private String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        String dateTimeString = getIntent().getStringExtra("spotcheckDate");
        dateTime = LocalDateTime.parse(dateTimeString);
        location = getIntent().getStringExtra("spotcheckLocation");
        carReg = getIntent().getStringExtra("spotcheckCarReg");
        carModel = getIntent().getStringExtra("spotcheckModel");
        result = getIntent().getStringExtra("spotcheckResult");
        notes = getIntent().getStringExtra("spotcheckNotes");

        TextView dateLabel = findViewById(R.id.dateLabel);
        TextView locationLabel = findViewById(R.id.locationLabel);
        TextView carModelLabel = findViewById(R.id.carModelLabel);
        TextView carRegLabel = findViewById(R.id.carRegLabel);
        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView notesLabel = findViewById(R.id.notesLabel);

        String dateText = dateTime.toLocalDate().toString() + " at " + dateTime.toLocalTime().toString().substring(0,5);
        dateLabel.setText(dateText);
        locationLabel.setText(location);
        carRegLabel.setText(carReg);
        carModelLabel.setText(carModel);
        resultLabel.setText(result);
        notesLabel.setText(notes);
    }

    public void startEditSpotCheck(View view) {
        // Start a EditItemActivity with the same data

        // In the Activity
            // User can change as he likes
            // When done presses button
            // Database is notified

        // Activity returns with new data
    }

    public void startDeleteSpotCheck(View view) {
        // Send data to onActivityResult
        Intent replyIntent = new Intent();
        replyIntent.putExtra("date", dateTime.toString());
        replyIntent.putExtra("location", location);
        replyIntent.putExtra("carReg", carReg);
        replyIntent.putExtra("carModel", carModel);
        replyIntent.putExtra("result", result);
        replyIntent.putExtra("notes", notes);
        replyIntent.putExtra("isDelete", true);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}