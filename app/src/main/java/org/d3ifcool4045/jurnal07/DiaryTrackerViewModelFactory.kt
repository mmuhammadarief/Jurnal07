package org.d3ifcool4045.jurnal07

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3ifcool4045.jurnal07.database.DiaryDatabaseDao

class DiaryTrackerViewModelFactory(private val dataSource : DiaryDatabaseDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiaryTrackerViewModel::class.java)) {
            return DiaryTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}