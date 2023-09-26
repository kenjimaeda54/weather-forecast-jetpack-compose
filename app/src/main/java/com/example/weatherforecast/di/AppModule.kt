package com.example.weatherforecast.di

import android.content.Context
import androidx.room.Room
import com.example.weatherforecast.data.ForecastDao
import com.example.weatherforecast.data.ForecastDatabase
import com.example.weatherforecast.repositories.ForecastRepositoryApi
import com.example.weatherforecast.services.ForecastService
import com.example.weatherforecast.utillity.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun watherForecastDao(forecastDatabase: ForecastDatabase): ForecastDao =
        forecastDatabase.ForecastDao()

    @Provides
    @Singleton
    fun weatherForecastDatabase(@ApplicationContext context: Context): ForecastDatabase =
        Room.databaseBuilder(
            context,
            ForecastDatabase::class.java,
            name = "forecast_db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun weatherForecastRepository(forecastService: ForecastService): ForecastRepositoryApi =
        ForecastRepositoryApi(forecastService)

    @Provides
    @Singleton
    fun weatherForecastService(): ForecastService = Retrofit.Builder().baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(ForecastService::class.java)

}