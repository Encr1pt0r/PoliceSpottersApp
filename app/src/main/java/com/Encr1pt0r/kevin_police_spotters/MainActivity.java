package com.Encr1pt0r.kevin_police_spotters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addSpotCheck(View view) {
        Intent addSpotCheckIntent = new Intent(this, NewEntryActivity.class);
        startActivity(addSpotCheckIntent);
    }
}