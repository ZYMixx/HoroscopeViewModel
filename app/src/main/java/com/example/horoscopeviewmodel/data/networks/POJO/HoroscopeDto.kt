package com.example.horoscopeviewmodel.data.networks.POJO

import android.util.Log
import com.google.gson.annotations.SerializedName

data class HoroscopeDto(
    @SerializedName("text")
    val horoscope: String,
    @SerializedName("meta")
    val meta: MetaDto? = null
) {
    init {
        Log.d("WHERE MY IMAGE", "$meta: ")
    }

}