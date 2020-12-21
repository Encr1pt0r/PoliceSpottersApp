package com.Encr1pt0r.kevin_police_spotters.model.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.Encr1pt0r.kevin_police_spotters.model.SpotCheck;
import com.Encr1pt0r.kevin_police_spotters.model.SpotCheckRepo;

import java.util.List;

public class SpotCheckVM extends AndroidViewModel {

    private SpotCheckRepo repo;
    private LiveData<List<SpotCheck>> allSpotChecks;

    public SpotCheckVM(Application application) {
        super(application);
        repo = new SpotCheckRepo(application);
        allSpotChecks = repo.getAllSpotChecks();
    }

    public LiveData<List<SpotCheck>> getAllSpotChecks() {
        return allSpotChecks;
    }

    public void insert(SpotCheck spotCheck) { repo.insertSpotCheck(spotCheck);  }

    public void update(SpotCheck spotCheck) { repo.updateSpotChecks(spotCheck); }

    public void delete(SpotCheck spotCheck) { repo.deleteSpotChecks(spotCheck); }
}
