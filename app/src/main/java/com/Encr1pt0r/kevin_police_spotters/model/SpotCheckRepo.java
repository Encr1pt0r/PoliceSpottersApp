package com.Encr1pt0r.kevin_police_spotters.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SpotCheckRepo {
    private SpotCheckDao spotCheckDao;
    private LiveData<List<SpotCheck>> allSpotChecks;

    public SpotCheckRepo(Application application) {
        SpotCheckDB db = SpotCheckDB.getDatabase(application);
        spotCheckDao = db.spotCheckDao();
        allSpotChecks = spotCheckDao.getAllSpotChecks();
    }

    public LiveData<List<SpotCheck>> getAllSpotChecks() {
        return allSpotChecks;
    }

    public void insertSpotCheck(SpotCheck spotCheck) {
        new InsertAsyncTask(spotCheckDao).execute(spotCheck);
    }

    /**
     * @TODO a Method that calls upon findChecksBetweenDates() in SpotCheckDao
     **/

    private static class InsertAsyncTask extends AsyncTask<SpotCheck, Void, Void> {
        private SpotCheckDao asyncTaskDao;

        InsertAsyncTask(SpotCheckDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SpotCheck... spotChecks) {
            asyncTaskDao.insert(spotChecks[0]);
            return null;
        }
    }
}
