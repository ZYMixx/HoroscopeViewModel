package com.example.horoscopeviewmodel.domain.usecase

import com.example.learnwordshelper.domain.HoroscopeRepository

class GetHoroscopeListUseCase(private val repository: HoroscopeRepository) {

    operator fun invoke () = repository.getHoroscopeList()

}