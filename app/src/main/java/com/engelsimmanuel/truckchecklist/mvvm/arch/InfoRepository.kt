package com.engelsimmanuel.truckchecklist.mvvm.arch

import com.engelsimmanuel.truckchecklist.database.utils.InfoDao
import androidx.lifecycle.LiveData
import com.engelsimmanuel.truckchecklist.database.utils.Info

class InfoRepository(private val infoDao: InfoDao) {

    val allInfo: LiveData<List<Info>> = infoDao.allInfo()

    suspend fun insert(info: Info?) {
        infoDao.insert(info)
    }

    suspend fun update(info: Info?) {
        infoDao.update(info)
    }

    suspend fun delete(info: Info?) {
        infoDao.delete(info)
    }

    suspend fun deleteAllInfo() {
        infoDao.deleteAllInfo()
    }
}