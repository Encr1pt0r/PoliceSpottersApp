package com.Encr1pt0r.kevin_police_spotters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
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

        dateLabel.setText(dateTime.toString());
        locationLabel.setText(location);
        carRegLabel.setText(carReg);
        carModelLabel.setText(carModel);
        resultLabel.setText(result);
        notesLabel.setText(notes);
    }
}