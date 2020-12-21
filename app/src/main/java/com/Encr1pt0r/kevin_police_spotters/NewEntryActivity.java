package com.Encr1pt0r.kevin_police_spotters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        /**
         * https://www.tutlane.com/tutorial/android/android-spinner-dropdown-list-with-examples
         * something I found to help with the result stuff
         * https://developer.android.com/guide/topics/ui/controls/spinner
         * Just for you now to follow the second half of this stuff
         */

        // Link String array file to spinner component
        Spinner spinner = (Spinner) findViewById(R.id.result_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.results_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    // Generate data for return to MainActivity
    public void saveNewSpotCheck(View view) {
        // Find components
        EditText locationET = findViewById(R.id.editTextLocation);
        EditText carRegET = findViewById(R.id.editTextCarRegNo);
        EditText carModelET = findViewById(R.id.editTextCarModel);
        Spinner resultSpinner = (Spinner) findViewById(R.id.result_spinner);
        EditText notesET = findViewById(R.id.editTextNotes);

        // Generate Strings
        String location = locationET.getText().toString();
        String carReg = carRegET.getText().toString();
        String carModel = carModelET.getText().toString();
        String result = resultSpinner.getSelectedItem().toString();
        String notes = notesET.getText().toString();

        // Debugging
        Log.i("debug", location);
        Log.i("debug", carReg);
        Log.i("debug", carModel);
        Log.i("debug", result);
        Log.i("debug", notes);

        // Add extras and finish Intent
        Intent replyIntent = new Intent();
        replyIntent.putExtra("location", location);
        replyIntent.putExtra("carReg", carReg);
        replyIntent.putExtra("carModel", carModel);
        replyIntent.putExtra("result", result);
        replyIntent.putExtra("notes", notes);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}