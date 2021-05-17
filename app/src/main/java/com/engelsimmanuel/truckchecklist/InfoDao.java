package com.engelsimmanuel.truckchecklist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// DAO class to provide methods that the rest of the app uses to interact with data
@Dao
public interface InfoDao {

    @Insert
    void insert(Info info);

    @Update
    void update(Info info);

    @Delete
    void delete(Info info);

    @Query("DELETE FROM info_table")
    void deleteAllInfo();

    @Query("SELECT * FROM info_table ORDER BY uid DESC")
    LiveData<List<Info>> getAllInfo();

}
