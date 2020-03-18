package org.d3ifcool4045.jurnal07

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.*
import org.d3ifcool4045.jurnal07.database.DiaryDatabaseDao


class DiaryFormViewModel(val database: DiaryDatabaseDao, val diary: Diary, application: Application) : AndroidViewModel(application){
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onSave(){
        uiScope.launch {
            insert(diary)
        }
    }

    private suspend fun insert(diary : Diary){
        withContext(Dispatchers.IO){
            database.insertDiary(diary)
        }
    }

}