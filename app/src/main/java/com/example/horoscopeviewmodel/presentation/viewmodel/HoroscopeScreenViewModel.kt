package com.example.horoscopeviewmodel.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.horoscopeviewmodel.domain.HoroscopeDate
import com.example.horoscopeviewmodel.domain.ZodiacSigns
import com.example.horoscopeviewmodel.domain.usecase.GetHoroscopeUseCase
import com.example.learnwordshelper.data.repository.HoroscopeRepositoryImpl
import com.example.learnwordshelper.domain.Horoscope
import kotlinx.coroutines.launch

class HoroscopeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HoroscopeRepositoryImpl(application)

    private val getHoroscopeUseCase = GetHoroscopeUseCase(repository)

    private val _horoscopeLD = MutableLiveData<Horoscope>()
    val horoscopeLD: LiveData<Horoscope>
        get() = _horoscopeLD

    fun getHoroscope(sign: ZodiacSigns, date: HoroscopeDate) {
        viewModelScope.launch {
            _horoscopeLD.postValue(
                getHoroscopeUseCase.invoke(
                    time = date.dateName,
                    zodiacSign = sign.singName
                )
            )
        }
    }

}