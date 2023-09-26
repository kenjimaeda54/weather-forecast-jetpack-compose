package com.example.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforecast.model.FavoritesModel


@Database(entities = [FavoritesModel::class], version = 1, exportSchema = false)
abstract class ForecastDatabase: RoomDatabase() {
    abstract fun ForecastDao(): ForecastDao
}