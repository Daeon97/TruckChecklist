package com.engelsimmanuel.truckchecklist.database.utils

import androidx.lifecycle.LiveData
import androidx.room.*

// DAO class to provide methods that the rest of the app uses to interact with data
@Dao
interface InfoDao {
    @Insert
    suspend fun insert(info: Info?)

    @Update
    suspend fun update(info: Info?)

    @Delete
    suspend fun delete(info: Info?)

    @Query("DELETE FROM info_table")
    suspend fun deleteAllInfo()

    @Query("SELECT * FROM info_table ORDER BY uid DESC")
    fun allInfo(): LiveData<List<Info>>
}