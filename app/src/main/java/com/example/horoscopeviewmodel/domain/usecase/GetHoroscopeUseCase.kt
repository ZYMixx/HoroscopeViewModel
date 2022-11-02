package com.example.horoscopeviewmodel.domain.usecase

import com.example.learnwordshelper.domain.HoroscopeRepository

class GetHoroscopeUseCase(private val repository: HoroscopeRepository) {

    suspend operator fun invoke(time: String, zodiacSign: String) = repository.getHoroscope(time,zodiacSign)

}