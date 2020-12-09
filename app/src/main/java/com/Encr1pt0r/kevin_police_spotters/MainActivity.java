package com.Encr1pt0r.kevin_police_spotters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Encr1pt0r.kevin_police_spotters.model.SpotCheck;
import com.Encr1pt0r.kevin_police_spotters.model.viewmodel.SpotCheckVM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * @TODO RecyclerView integration for Room (TouchableOpacity from React?)
     */

    private SpotCheckVM spotCheckVM;
    ArrayList<String[]> spotCheckList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        spotCheckVM = ViewModelProviders.of(this).get(SpotCheckVM.class);
        spotCheckVM.getAllSpotChecks().observe(this, new Observer<List<SpotCheck>>() {
            @Override
            public void onChanged(List<SpotCheck> spotChecks) {
                String listAsString = "";
                for(SpotCheck spotCheck : spotChecks) {
                    // This is where the RecyclerView comes in
                    listAsString += spotCheck.getDateTime() + " " + spotCheck.getCarRegNo() + "\n";
                }
                TextView outputTV = findViewById(R.id.TestTV);
                outputTV.setText(listAsString);
            }
        });
    }

    public void addSpotCheck(View view) {
        Intent addSpotCheckIntent = new Intent(this, NewEntryActivity.class);
        startActivityForResult(addSpotCheckIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Date newDate = new Date();
        String location = data.getStringExtra("location");
        String carReg = data.getStringExtra("carReg");
        String carModel = data.getStringExtra("carModel");
        String result = data.getStringExtra("result");
        String notes = data.getStringExtra("notes");

        // Create SpotCheck and add to ViewModel
        SpotCheck spotCheck = new SpotCheck(newDate, location, carReg, carModel, result, notes);
        spotCheckVM.insert(spotCheck);
    }
}