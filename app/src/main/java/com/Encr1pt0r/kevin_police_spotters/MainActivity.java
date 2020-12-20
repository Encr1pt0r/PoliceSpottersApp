package com.Encr1pt0r.kevin_police_spotters;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Encr1pt0r.kevin_police_spotters.model.SpotCheck;
import com.Encr1pt0r.kevin_police_spotters.model.viewmodel.SpotCheckVM;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private SpotCheckVM spotCheckVM;
    List<SpotCheck> spotCheckList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SpotCheckListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        spotCheckVM = ViewModelProviders.of(this).get(SpotCheckVM.class);
        spotCheckVM.getAllSpotChecks().observe(this, new Observer<List<SpotCheck>>() {
            @Override
            public void onChanged(List<SpotCheck> spotChecks) {
                // When the database is updated the main List is updated for the RecyclerView
                spotCheckList = spotChecks;
                Log.i("debug", String.valueOf(spotCheckList));
                adapter.setSpotCheckList(spotCheckList);
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new SpotCheckListAdapter(this, spotCheckList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addSpotCheck(View view) {
        Intent addSpotCheckIntent = new Intent(this, NewEntryActivity.class);
        startActivityForResult(addSpotCheckIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Data from Intent generated
        LocalDateTime date;
        String location = data.getStringExtra("location");
        String carReg = data.getStringExtra("carReg");
        String carModel = data.getStringExtra("carModel");
        String result = data.getStringExtra("result");
        String notes = data.getStringExtra("notes");

        if(requestCode == 0) {
            // Create SpotCheck and add to ViewModel
            date = LocalDateTime.now();
            SpotCheck spotCheck = new SpotCheck(date, location, carReg, carModel, result, notes);
            spotCheckVM.insert(spotCheck);
            Toast notice = Toast.makeText(this, "Spot Check Inserted", Toast.LENGTH_SHORT);
            notice.show();
            Log.i("debug", "SpotCheck " + spotCheck.getCarRegNo() + " inserted");

        } else if(requestCode == 1) {
            // Date is extracted from Intent
            String dateTimeString = data.getStringExtra("date");
            Log.i("debug", dateTimeString);
            date = LocalDateTime.parse(dateTimeString);

            // Boolean extra to decide to delete or update
            boolean isDelete = data.getBooleanExtra("isDelete", false);

            if(isDelete) {
                SpotCheck spotCheck = new SpotCheck(date, location, carReg, carModel, result, notes);
                spotCheckVM.delete(spotCheck);
                Toast notice = Toast.makeText(this, "Spot Check Deleted", Toast.LENGTH_SHORT);
                notice.show();
                Log.i("debug", "SpotCheck " + spotCheck.getCarRegNo() + " deleted");
            } else {
                // if false then it is updated
            }
        }
    }

    public void shareSpotChecks(View view) {
        // Generate String body in some sort of loop
        String body = "";
        for(SpotCheck spotCheck : spotCheckList) {
            body += "\nOccurred: " + spotCheck.getDateTime().toLocalDate().toString() + " at " + spotCheck.getDateTime().toLocalTime().toString().substring(0,5) + "\n";
            body += "Location: " + spotCheck.getLocation() + "\n";
            body += "Car Reg No.: " + spotCheck.getCarRegNo() + "\n";
            body += "Make/Model: " + spotCheck.getMakeOfCar() + "\n";
            body += "Result: " + spotCheck.getResult() + "\n";
            body += "Notes: \n" + spotCheck.getNotes() + "\n";
        }

        // Load implicit intent to mail app
        Uri mailURI = Uri.parse("mailto:fred@test.com");
        String subject = "SpotChecks " + LocalDate.now().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, mailURI);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(emailIntent);
    }

    // adapter for RecyclerView
    public class SpotCheckListAdapter extends RecyclerView.Adapter<SpotCheckListAdapter.SpotCheckViewHolder> {

        private final LayoutInflater inflater;
        private List<SpotCheck> spotCheckList;
        private Context context;

        public SpotCheckListAdapter(Context context, List<SpotCheck> spotCheckList) {
            inflater = LayoutInflater.from(context);
            this.spotCheckList = spotCheckList;
            this.context = context;
        }

        @NonNull
        @Override
        public SpotCheckListAdapter.SpotCheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.spotcheck_item, parent, false);
            return new SpotCheckViewHolder(itemView, adapter, context);
        }

        @Override
        public void onBindViewHolder(@NonNull SpotCheckListAdapter.SpotCheckViewHolder holder, int position) {
            SpotCheck currentSpotCheck = spotCheckList.get(position);

            Log.i("debug", currentSpotCheck.getDateTime().toLocalDate().toString());

            // Set text for other elements
            holder.dateTV.setText(currentSpotCheck.getDateTime().toLocalDate().toString());
            holder.timeTV.setText(currentSpotCheck.getDateTime().toLocalTime().toString().substring(0,5));
            holder.carRegTV.setText(currentSpotCheck.getCarRegNo());
            holder.carModelTV.setText(currentSpotCheck.getMakeOfCar());
        }

        void setSpotCheckList(List<SpotCheck> spotCheckList) {
            this.spotCheckList = spotCheckList;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (spotCheckList != null) {
                return spotCheckList.size();
            } else return 0;

        }

        public class SpotCheckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public final TextView dateTV;
            public final TextView timeTV;
            public final TextView carRegTV;
            public final TextView carModelTV;
            public SpotCheckListAdapter adapter;
            public Context context;

            public SpotCheckViewHolder(@NonNull View itemView, SpotCheckListAdapter adapter, Context context) {
                super(itemView);
                dateTV = itemView.findViewById(R.id.dateTextView);
                timeTV = itemView.findViewById(R.id.timeTextView);
                carRegTV = itemView.findViewById(R.id.carRegNumTextView);
                carModelTV = itemView.findViewById(R.id.carModelTextView);
                this.adapter = adapter;
                this.context = context;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                // Isolate SpotCheck to pass into Intent
                int position = getLayoutPosition();
                SpotCheck current = spotCheckList.get(position);

                // debugging
                Log.i("debug", current.getDateTime().toString() + " " + current.getCarRegNo() + " was just pressed");

                // Create Intent with SpotCheck data in it
                Intent viewSpotCheckItem = new Intent(context, ViewItemActivity.class);
                viewSpotCheckItem.putExtra("spotcheckDate", current.getDateTime().toString());
                viewSpotCheckItem.putExtra("spotcheckLocation", current.getLocation());
                viewSpotCheckItem.putExtra("spotcheckCarReg", current.getCarRegNo());
                viewSpotCheckItem.putExtra("spotcheckModel", current.getMakeOfCar());
                viewSpotCheckItem.putExtra("spotcheckResult", current.getResult());
                viewSpotCheckItem.putExtra("spotcheckNotes", current.getNotes());
                startActivityForResult(viewSpotCheckItem, 1);
            }
        }
    }
}