package com.example.learnwordshelper.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.horoscopeviewmodel.data.maper.HoroscopeMapper
import com.example.horoscopeviewmodel.data.networks.ApiFactory
import com.example.learnwordshelper.data.database.AppDatabase
import com.example.learnwordshelper.domain.Horoscope
import com.example.learnwordshelper.domain.HoroscopeRepository

class HoroscopeRepositoryImpl(application: Application) : HoroscopeRepository {

    private val horoscopeDao = AppDatabase.getInstance(application).horoscopeDao()
    private val mapper = HoroscopeMapper()

    override suspend fun getHoroscope(time : String, sign: String): Horoscope {
        val horoscope = horoscopeDao.getHoroscope(time, sign)
        return if (horoscope == null){
            val horoscopeDto = ApiFactory.apiService.getHoroscope(
                sign = sign,
                day = time
            )
            horoscopeDao.addHoroscope(mapper.mapDtoToDbModel(
                horoscopeDto,
                sign,
                time
            ))
            mapper.mapDtoToEntity(horoscopeDto, sign, time)
        } else{
            mapper.mapDbModelToEntity(horoscope)
        }
    }

    override fun getHoroscopeList(): LiveData<List<Horoscope>> {
        return Transformations.map(horoscopeDao.getHoroscopeList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun editHoroscope(horoscope: Horoscope) {
        horoscopeDao.editHoroscope(mapper.mapEntityToDbModel(horoscope))
    }

}