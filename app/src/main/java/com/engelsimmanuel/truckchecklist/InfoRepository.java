package com.engelsimmanuel.truckchecklist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class InfoRepository {
    private InfoDao infoDao;
    private LiveData<List<Info>> allInfo;

    public InfoRepository(Application application) {
        InfoDatabase infoDatabase = InfoDatabase.getInstance(application);
        infoDao = infoDatabase.infoDao();
        allInfo = infoDao.getAllInfo();
    }

    public void insert(Info info) {
        new InsertInfoAsyncTask(infoDao).execute(info);
    }

    public void update(Info info) {
        new UpdateInfoAsyncTask(infoDao).execute(info);
    }

    public void delete(Info info) {
        new DeleteInfoAsyncTask(infoDao).execute(info);
    }

    public void deleteAllInfo() {
        new DeleteAllInfoAsyncTask(infoDao).execute();
    }

    public LiveData<List<Info>> getAllInfo() {
        return allInfo;
    }

    private static class InsertInfoAsyncTask extends AsyncTask<Info, Void, Void> {
        private InfoDao infoDao;

        private InsertInfoAsyncTask(InfoDao infoDao) {
            this.infoDao = infoDao;
        }

        @Override
        protected Void doInBackground(Info... infos) {
            infoDao.insert(infos[0]);
            return null;
        }
    }

    private static class UpdateInfoAsyncTask extends AsyncTask<Info, Void, Void> {
        private InfoDao infoDao;

        private UpdateInfoAsyncTask(InfoDao infoDao) {
            this.infoDao = infoDao;
        }

        @Override
        protected Void doInBackground(Info... infos) {
            infoDao.update(infos[0]);
            return null;
        }
    }

    private static class DeleteInfoAsyncTask extends AsyncTask<Info, Void, Void> {
        private InfoDao infoDao;

        private DeleteInfoAsyncTask(InfoDao infoDao) {
            this.infoDao = infoDao;
        }

        @Override
        protected Void doInBackground(Info... infos) {
            infoDao.delete(infos[0]);
            return null;
        }
    }

    private static class DeleteAllInfoAsyncTask extends AsyncTask<Void, Void, Void> {
        private InfoDao infoDao;

        private DeleteAllInfoAsyncTask(InfoDao infoDao) {
            this.infoDao = infoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            infoDao.deleteAllInfo();
            return null;
        }
    }
}
