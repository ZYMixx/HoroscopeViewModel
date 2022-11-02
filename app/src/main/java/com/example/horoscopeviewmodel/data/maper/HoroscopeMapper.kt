package com.example.horoscopeviewmodel.data.maper

import com.example.horoscopeviewmodel.data.networks.POJO.HoroscopeDto
import com.example.learnwordshelper.data.database.dbmobel.HoroscopeDbModel
import com.example.learnwordshelper.domain.Horoscope
import kotlin.math.sign

class HoroscopeMapper {

    fun mapDtoToEntity(dto: HoroscopeDto, sign: String, time: String) = Horoscope(
        horoscope = dto.horoscope,
        zodiacSign = sign,
        image = dto.meta?.image,
        date = time
    )

    fun mapDtoToDbModel(dto: HoroscopeDto, sign: String, currentDate: String) = HoroscopeDbModel(
        id = 0,
        horoscope = dto.horoscope,
        zodiacSign = sign,
        image = dto.meta?.image,
        currentDate = currentDate
    )

    fun mapDbModelToEntity(dbModel: HoroscopeDbModel) = Horoscope(
        horoscope = dbModel.horoscope,
        zodiacSign = dbModel.zodiacSign,
        image = dbModel.image,
        horoscopeResult = dbModel.horoscopeResult,
        date = dbModel.currentDate
    )

    fun mapEntityToDbModel(entity: Horoscope) = HoroscopeDbModel(
        id = 0,
        horoscope = entity.horoscope,
        zodiacSign = entity.zodiacSign,
        image = entity.image,
        currentDate = entity.date,
        horoscopeResult = entity.horoscopeResult
    )

}