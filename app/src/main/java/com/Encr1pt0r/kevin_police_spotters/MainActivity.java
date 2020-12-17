package com.Encr1pt0r.kevin_police_spotters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Encr1pt0r.kevin_police_spotters.model.SpotCheck;
import com.Encr1pt0r.kevin_police_spotters.model.viewmodel.SpotCheckVM;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * @TODO RecyclerView integration for Room (TouchableOpacity from React?) Done
     */

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
                Log.i("debug",spotChecks.get(0).getDateTime().getDate() + "/" + spotChecks.get(0).getDateTime().getMonth() + "/" + spotChecks.get(0).getDateTime().getYear());
                Log.i("debug",spotChecks.get(0).getDateTime().getHours() + ":" + spotChecks.get(0).getDateTime().getMinutes());

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

    // adapter for RecyclerView
    public class SpotCheckListAdapter extends RecyclerView.Adapter<SpotCheckListAdapter.SpotCheckViewHolder> {

        private final LayoutInflater inflater;
        private List<SpotCheck> spotCheckList;

        public SpotCheckListAdapter(Context context, List<SpotCheck> spotCheckList) {
            inflater = LayoutInflater.from(context);
            this.spotCheckList = spotCheckList;
        }

        @NonNull
        @Override
        public SpotCheckListAdapter.SpotCheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.spotcheck_item, parent, false);
            return new SpotCheckViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull SpotCheckListAdapter.SpotCheckViewHolder holder, int position) {
            SpotCheck currentSpotCheck = spotCheckList.get(position);

            Log.i("debug", currentSpotCheck.getDateTime().getDate() + " / " + currentSpotCheck.getDateTime().getMonth() + " / " + currentSpotCheck.getDateTime().getYear());

            // Set text for other elements
            holder.dateTV.setText(currentSpotCheck.getDateTime().getDate() + "/" + currentSpotCheck.getDateTime().getMonth() + "/" + currentSpotCheck.getDateTime().getYear());
            holder.timeTV.setText(currentSpotCheck.getDateTime().getHours() + ":" + currentSpotCheck.getDateTime().getMinutes());
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

        public class SpotCheckViewHolder extends RecyclerView.ViewHolder {
            public final TextView dateTV;
            public final TextView timeTV;
            public final TextView carRegTV;
            public final TextView carModelTV;

            public SpotCheckViewHolder(@NonNull View itemView) {
                super(itemView);
                dateTV = itemView.findViewById(R.id.dateTextView);
                timeTV = itemView.findViewById(R.id.timeTextView);
                carRegTV = itemView.findViewById(R.id.carRegNumTextView);
                carModelTV = itemView.findViewById(R.id.carModelTextView);
            }
        }
    }
    }