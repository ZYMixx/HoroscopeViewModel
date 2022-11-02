package com.example.learnwordshelper.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.learnwordshelper.data.database.dbmobel.HoroscopeDbModel
import com.example.learnwordshelper.domain.Horoscope

@Dao
interface HoroscopeDao {

    @Query("SELECT * FROM full_horoscope_list ORDER BY id")
    fun getHoroscopeList(): LiveData<List<HoroscopeDbModel>>

    @Query(
        "SELECT * FROM full_horoscope_list WHERE currentDate == :time " +
                "AND zodiacSign == :zodiacSign LIMIT 1"
    )
    suspend fun getHoroscope(time: String, zodiacSign: String): HoroscopeDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHoroscope(horoscopeDbModel: HoroscopeDbModel)

    @Update()
    suspend fun editHoroscope(horoscopeDbModel: HoroscopeDbModel)

}