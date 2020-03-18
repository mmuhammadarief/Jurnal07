package org.d3ifcool4045.jurnal07



@Entity(tableName = "daily_diary")
data class Diary(

    @PrimaryKey(autoGenerate = true)
    var diaryId: Long = 0L,

    @ColumnInfo(name = "date_diary")
    var dateDiary: String = "",

    @ColumnInfo(name = "text_diary")
    var textDiary: String = ""

)