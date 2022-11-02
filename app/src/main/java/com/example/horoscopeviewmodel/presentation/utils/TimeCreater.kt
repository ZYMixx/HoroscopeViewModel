package com.example.horoscopeviewmodel.presentation.utils

import com.example.horoscopeviewmodel.domain.HoroscopeDate
import java.time.LocalDateTime

object TimeCreater {

    fun getDate(date: HoroscopeDate): String {
        return when (date) {
            HoroscopeDate.TODAY -> {
                var time = LocalDateTime.now()
                "${time.dayOfMonth}.${time.monthValue}"
            }
            HoroscopeDate.TOMORROW -> {
                var time = LocalDateTime.now().plusDays(1)
                "${time.dayOfMonth}.${time.monthValue}"
            }
        }
    }

    fun getDate(stringDate: String): String {
        if (stringDate == HoroscopeDate.TODAY.dateName) {
            return getDate(HoroscopeDate.TODAY)
        }
        if (stringDate == HoroscopeDate.TODAY.dateName) {
            return getDate(HoroscopeDate.TOMORROW)
        }
        return "unknown"
    }
}