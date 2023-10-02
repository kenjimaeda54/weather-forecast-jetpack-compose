package com.example.weatherforecast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherforecast.model.FavoritesModel
import com.example.weatherforecast.model.UnitModel
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


    //Seetting
    @Query("SELECT * from unit")
    fun getAllUnits(): Flow<List<UnitModel>>

    @Query("SELECT * from unit where uuid =:uuid")
    suspend fun getOneUnit(uuid: UUID): UnitModel

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: UnitModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUnit(unit: UnitModel)

    @Query("DELETE from unit")
    suspend  fun deleteAllUnits()

    @Delete
    suspend fun deleteOneUnit(unit:  UnitModel)

}