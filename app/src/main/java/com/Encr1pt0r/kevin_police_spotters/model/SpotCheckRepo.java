package com.Encr1pt0r.kevin_police_spotters.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * @TODO a Method that calls upon findChecksBetweenDates() in SpotCheckDao
 **/

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

    /** add a way to update elements */
    public void updateSpotChecks(SpotCheck spotCheck) {
        new UpdateAsyncTask(spotCheckDao).execute(spotCheck);
    }
    /** add a way to delete elements */
    public void deleteSpotChecks(SpotCheck spotCheck) {
        new DeleteAsyncTask(spotCheckDao).execute(spotCheck);
    }

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

    private static class UpdateAsyncTask extends AsyncTask<SpotCheck, Void, Void> {
        private SpotCheckDao asyncTaskDao;

        UpdateAsyncTask(SpotCheckDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SpotCheck... spotChecks) {
            asyncTaskDao.updateSpotChecks(spotChecks);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<SpotCheck, Void, Void> {
        private SpotCheckDao asyncTaskDao;

        DeleteAsyncTask(SpotCheckDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SpotCheck... spotChecks) {
            asyncTaskDao.deleteSpotChecks(spotChecks);
            return null;
        }
    }
}
