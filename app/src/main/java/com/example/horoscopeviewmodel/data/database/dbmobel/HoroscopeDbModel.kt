package com.example.learnwordshelper.data.database.dbmobel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_horoscope_list", primaryKeys = ["currentDate", "zodiacSign"])
data class HoroscopeDbModel(
    var id: Int,
    var currentDate: String,
    var zodiacSign: String,
    var horoscope: String,
    var image: String?,
    var horoscopeResult: Boolean? = null
) {
}