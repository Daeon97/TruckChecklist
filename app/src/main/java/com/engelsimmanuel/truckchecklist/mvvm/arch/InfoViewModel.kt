package com.engelsimmanuel.truckchecklist.mvvm.arch;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.engelsimmanuel.truckchecklist.database.utils.Info;

import java.util.List;

public class InfoViewModel extends AndroidViewModel {
    private InfoRepository infoRepository;
    private LiveData<List<Info>> allInfo;

    public InfoViewModel(@NonNull Application application) {
        super(application);
        infoRepository = new InfoRepository(application);
        allInfo = infoRepository.getAllInfo();
    }

    public void insert(Info info) {
        infoRepository.insert(info);
    }

    public void update(Info info) {
        infoRepository.update(info);
    }

    public void delete(Info info) {
        infoRepository.delete(info);
    }

    public void deleteAllInfo() {
        infoRepository.deleteAllInfo();
    }

    public LiveData<List<Info>> getAllInfo() {
        return allInfo;
    }
}
