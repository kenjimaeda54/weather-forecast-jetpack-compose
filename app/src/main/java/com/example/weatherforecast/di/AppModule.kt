package com.example.weatherforecast.di

import com.example.weatherforecast.repositories.ForecastRepository
import com.example.weatherforecast.services.ForecastService
import com.example.weatherforecast.utillity.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun weatherForecastRepository(forecastService: ForecastService): ForecastRepository =
        ForecastRepository(forecastService)

    @Provides
    @Singleton
    fun weatherForecastService(): ForecastService = Retrofit.Builder().baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(ForecastService::class.java)

}