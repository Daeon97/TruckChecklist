package com.engelsimmanuel.truckchecklist.mvvm.arch

import android.app.Application
import androidx.lifecycle.*
import com.engelsimmanuel.truckchecklist.database.utils.Info
import com.engelsimmanuel.truckchecklist.database.utils.InfoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoViewModel(application: Application) : AndroidViewModel(application) {

    val allInfo: LiveData<List<Info>>
    private val infoRepository: InfoRepository

    init {
        val infoDao = InfoDatabase.getInstance(application).infoDao()
        infoRepository = InfoRepository(infoDao)
        allInfo = infoRepository.allInfo
    }

    fun insert(info: Info?) {
        viewModelScope.launch(Dispatchers.IO) {
            infoRepository.insert(info)
        }
    }

    fun update(info: Info?) {
        viewModelScope.launch(Dispatchers.IO) {
            infoRepository.update(info)
        }
    }

    fun delete(info: Info?) {
        viewModelScope.launch(Dispatchers.IO) {
            infoRepository.delete(info)
        }
    }

    fun deleteAllInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            infoRepository.deleteAllInfo()
        }
    }
}

class InfoViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoViewModel::class.java)) {
            return InfoViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}