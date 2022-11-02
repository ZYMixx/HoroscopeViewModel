package com.example.learnwordshelper.domain

data class Horoscope(
    var zodiacSign: String,
    var horoscope: String,
    var image: String?,
    var date: String,
    var horoscopeResult: Boolean? = null
) {
}