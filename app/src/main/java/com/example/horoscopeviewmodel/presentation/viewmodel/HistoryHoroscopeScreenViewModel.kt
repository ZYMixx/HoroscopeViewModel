package com.example.horoscopeviewmodel.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.horoscopeviewmodel.domain.usecase.EditHoroscopeUseCase
import com.example.horoscopeviewmodel.domain.usecase.GetHoroscopeListUseCase
import com.example.horoscopeviewmodel.domain.usecase.GetHoroscopeUseCase
import com.example.learnwordshelper.data.repository.HoroscopeRepositoryImpl
import com.example.learnwordshelper.domain.Horoscope
import kotlinx.coroutines.launch

class HistoryHoroscopeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HoroscopeRepositoryImpl(application)

    private val getHoroscopeListUseCase = GetHoroscopeListUseCase(repository)
    private val editHoroscopeUseCase = EditHoroscopeUseCase(repository)

    val horoscopeListLD: LiveData<List<Horoscope>> = getHoroscopeListUseCase.invoke()

    fun editHoroscopeResults(horoscope: Horoscope, result: Boolean?){
        val newHoroscope = horoscope.copy(horoscopeResult = result)
        viewModelScope.launch {
            editHoroscopeUseCase(newHoroscope)
        }

    }

}