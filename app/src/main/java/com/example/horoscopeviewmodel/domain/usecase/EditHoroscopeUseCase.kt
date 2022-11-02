package com.example.horoscopeviewmodel.domain.usecase

import com.example.learnwordshelper.domain.Horoscope
import com.example.learnwordshelper.domain.HoroscopeRepository

class EditHoroscopeUseCase(private val repository: HoroscopeRepository) {

    suspend operator fun invoke (horoscope: Horoscope) = repository.editHoroscope(horoscope)

}