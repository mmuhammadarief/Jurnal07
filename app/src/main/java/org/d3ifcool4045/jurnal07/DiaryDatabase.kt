package org.d3ifcool4045.jurnal07.database

import android.content.Context

import org.d3ifcool4045.jurnal07.Diary


@Database(entities = [Diary::class], version = 1,exportSchema = false)
abstract class DiaryDatabase : RoomDatabase(){

    abstract val diaryDatabaseDao: DiaryDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: DiaryDatabase? = null

        fun getInstance(context: Context) : DiaryDatabase{

            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,DiaryDatabase::class.java,"diary_history_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }

        }
    }

}