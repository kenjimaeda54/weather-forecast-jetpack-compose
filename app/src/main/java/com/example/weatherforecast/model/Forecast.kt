package com.example.weatherforecast.model

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: kotlin.collections.List<List>,
    val message: Double
)