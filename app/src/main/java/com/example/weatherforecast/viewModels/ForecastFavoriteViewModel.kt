package com.example.weatherforecast.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.model.FavoritesModel
import com.example.weatherforecast.repositories.ForecastRepositoryDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForecastFavoriteViewModel @Inject constructor(private val forecastRepositoryDatabase: ForecastRepositoryDatabase) :
    ViewModel() {
    private val _listFavorite = MutableStateFlow<List<FavoritesModel>>(emptyList())
    var listFavorite = _listFavorite.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            forecastRepositoryDatabase.getAllFavorites().distinctUntilChanged()
                .collect { listFavorite ->
                    if (listFavorite.isEmpty()) {
                        Log.d("Favorite", "Favorite is empty")
                    } else {
                        _listFavorite.value = listFavorite
                    }

                }
        }
    }

    fun insertFavorite(favoritesModel: FavoritesModel) =
        viewModelScope.launch { forecastRepositoryDatabase.insertFavorite(favoritesModel) }

    fun updateFavorite(favoritesModel: FavoritesModel) =
        viewModelScope.launch { forecastRepositoryDatabase.updateFavorite(favoritesModel) }

    fun deleteOneFavorite(favoritesModel: FavoritesModel) =
        viewModelScope.launch { forecastRepositoryDatabase.deleteOneFavorite(favoritesModel) }


}