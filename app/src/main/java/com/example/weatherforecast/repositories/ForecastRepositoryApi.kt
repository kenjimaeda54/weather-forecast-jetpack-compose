package com.example.weatherforecast.repositories

import android.util.Log
import com.example.weatherforecast.data.DataOrExpection
import com.example.weatherforecast.model.Forecast
import com.example.weatherforecast.services.ForecastService
import javax.inject.Inject


class ForecastRepositoryApi @Inject constructor(private val forecastService: ForecastService) {

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