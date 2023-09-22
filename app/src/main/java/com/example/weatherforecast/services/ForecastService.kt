package com.example.weatherforecast.services

import com.example.weatherforecast.utillity.Constants
import com.example.weatherforecast.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("forecast/daily")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("count") count: Int = 6,
        @Query("appid") appid: String = Constants.tokenId,
        @Query("units") units: String = "imperial"
    ): Forecast
}