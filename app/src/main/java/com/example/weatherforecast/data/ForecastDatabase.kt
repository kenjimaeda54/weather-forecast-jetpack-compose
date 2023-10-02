package com.example.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforecast.model.FavoritesModel
import com.example.weatherforecast.model.UnitModel


//toda vez qeu adiciona algo novo do database preciso mudar a versao por isso agora e o 2
@Database(entities = [FavoritesModel::class,UnitModel::class], version = 2, exportSchema = false)
abstract class ForecastDatabase: RoomDatabase() {
    abstract fun ForecastDao(): ForecastDao
}