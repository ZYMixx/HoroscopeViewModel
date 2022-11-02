package com.example.horoscopeviewmodel.data.networks

import com.example.horoscopeviewmodel.data.networks.POJO.HoroscopeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeAPI {

    @GET("api/front/v1/horoscope/{day}/{sign}/")
    suspend fun getHoroscope(
        @Path(QUERY_PARAM_DAY) day: String = "today",
        @Path(QUERY_PARAM_SIGN) sign: String = "taurus"
    ): HoroscopeDto

    companion object {
        private const val QUERY_PARAM_SIGN = "sign"
        private const val QUERY_PARAM_DAY = "day"
    }

}