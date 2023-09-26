package com.example.weatherforecast.repositories

import com.example.weatherforecast.data.ForecastDao
import com.example.weatherforecast.model.FavoritesModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ForecastRepositoryDatabase @Inject constructor(private val forecastDao: ForecastDao) {

    fun getAllFavorites(): Flow<List<FavoritesModel>> = forecastDao.getFavorites()
    suspend fun getOneFavorite(uuid: UUID): FavoritesModel = forecastDao.getOneFavorite(uuid)
    suspend fun updateFavorite(favoritesModel: FavoritesModel) =
        forecastDao.updateFavorite(favoritesModel)

    suspend fun insertFavorite(favoritesModel: FavoritesModel) =
        forecastDao.addFavorite(favoritesModel)

    suspend fun deleteOneFavorite(favoritesModel: FavoritesModel) =
        forecastDao.deleteOneFavorite(favoritesModel)

    suspend fun deleteAllFavorite() = forecastDao.deleteAllFavorite()

}