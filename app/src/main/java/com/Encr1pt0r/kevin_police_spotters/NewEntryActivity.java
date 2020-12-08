package com.Encr1pt0r.kevin_police_spotters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        /**
         * @TODO - finish interactions with the spinner
         * https://www.tutlane.com/tutorial/android/android-spinner-dropdown-list-with-examples
         * something I found to help with the result stuff
         * Just for you now to follow the second half of this stuff
         */

        // Link String array file to spinner component
        Spinner spinner = (Spinner) findViewById(R.id.result_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.results_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}