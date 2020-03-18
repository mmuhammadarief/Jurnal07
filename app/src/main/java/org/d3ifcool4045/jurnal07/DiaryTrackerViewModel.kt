package org.d3ifcool4045.jurnal07

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.*
import org.d3ifcool4045.jurnal07.database.DiaryDatabaseDao
import java.lang.StringBuilder

class DiaryTrackerViewModel(val database: DiaryDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var diary = MutableLiveData<Diary?>()
    private val diaries = database.getAllDiary()

    val diariesString = Transformations.map(diaries){
        getList(it)
    }

    private fun getList(diaries: List<Diary>) : Spanned{
        val sb = StringBuilder()

        sb.apply {
            diaries.forEach{
                append("${it.dateDiary}<br>")
                append("${it.textDiary}<br>")
                append("<br>")
                append("<br>")
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        initializeDiary()
        println(diariesString)
    }

    private fun initializeDiary(){
        uiScope.launch {
            diary.value = getDiaryFromDatabase()
        }
    }

    private suspend fun getDiaryFromDatabase(): Diary? {
        return withContext(Dispatchers.IO){
            var diary = database.getDiary()
            if(diary?.dateDiary != diary?.dateDiary){
                diary = null
            }
            diary
        }
    }

    fun onClear(){
        uiScope.launch {
            clear()
            diary.value = null
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

}