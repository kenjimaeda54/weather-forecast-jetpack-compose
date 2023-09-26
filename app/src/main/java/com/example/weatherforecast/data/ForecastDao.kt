package com.example.weatherforecast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherforecast.model.FavoritesModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID


@Dao
interface ForecastDao {
    @Query("SELECT * from favorite")
    fun getFavorites(): Flow<List<FavoritesModel>>

    @Query("SELECT * from favorite where uid =:uid")
    suspend  fun getOneFavorite(uid: UUID): FavoritesModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addFavorite(favoritesModel: FavoritesModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  updateFavorite(favoritesModel: FavoritesModel)

    @Query("DELETE  from favorite")
    suspend fun  deleteAllFavorite()

    @Delete
    suspend fun  deleteOneFavorite(favoritesModel: FavoritesModel)

}