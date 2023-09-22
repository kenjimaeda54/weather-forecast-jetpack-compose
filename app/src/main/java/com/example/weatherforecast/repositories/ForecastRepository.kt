package com.example.weatherforecast.repositories

import android.util.Log
import com.example.weatherforecast.data.DataOrExpection
import com.example.weatherforecast.services.ForecastService
import com.example.weatherforecast.model.Forecast
import javax.inject.Inject


class ForecastRepository @Inject constructor(private val forecastService: ForecastService) {

    suspend fun getForecast(city: String): DataOrExpection<Forecast, Boolean, Exception> {
        val data = try {
            forecastService.getForecast(city)
        } catch (expection: Exception) {
            Log.d("Expection", expection.toString())
            return DataOrExpection(expection = expection)
        }
        return DataOrExpection(data = data)
    }

}