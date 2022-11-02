package com.example.learnwordshelper.domain

import androidx.lifecycle.LiveData

interface HoroscopeRepository {

    suspend fun getHoroscope(time : String, zodiacSign: String) : Horoscope

    fun getHoroscopeList() : LiveData<List<Horoscope>>

    suspend fun editHoroscope(horoscope: Horoscope)
}