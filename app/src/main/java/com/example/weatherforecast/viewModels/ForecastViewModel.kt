package com.example.weatherforecast.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.DataOrExpection
import com.example.weatherforecast.model.Forecast
import com.example.weatherforecast.model.UnitModel
import com.example.weatherforecast.repositories.ForecastRepositoryApi
import com.example.weatherforecast.repositories.ForecastRepositoryDatabase
import com.example.weatherforecast.utillity.UnitsTemperature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastRepositoryDatabase: ForecastRepositoryDatabase,
    private val forecastRepositoryApi: ForecastRepositoryApi
) :
    ViewModel() {
    private val _listUnits = MutableStateFlow<List<UnitModel>>(emptyList())
    val listUnits = _listUnits.asStateFlow()
    val data: MutableState<DataOrExpection<Forecast, Boolean, Exception>> = mutableStateOf(
        DataOrExpection(null, true, Exception(""))
    )


    fun getForecast(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            data.value.loading = true
            forecastRepositoryDatabase.getallUnits().distinctUntilChanged().collect {
                if (it.isEmpty()) {
                    Log.d("Units", "Units are empty")
                    data.value = forecastRepositoryApi.getForecast(city, unit = "imperial")
                    data.value.loading = false
                } else {
                    val unit =
                        if (it[0].unit == UnitsTemperature.Fahrenith.units) "imperial" else "metric"
                    data.value = forecastRepositoryApi.getForecast(city, unit)
                    data.value.loading = false
                    _listUnits.value = it
                }
            }
            if (data.value.toString().isNotEmpty()) data.value.loading = false
        }
    }

    fun insertUnit(unitModel: UnitModel) {
        viewModelScope.launch {
            forecastRepositoryDatabase.insertUnit(unitModel)
        }
    }

    fun updateUnit(unitModel: UnitModel) {
        viewModelScope.launch {
            forecastRepositoryDatabase.updateUnit(unitModel)
        }
    }

    fun deleteOneUnit(unitModel: UnitModel) {
        viewModelScope.launch {
            forecastRepositoryDatabase.deleteOneUnit(unitModel)
        }
    }

    fun deleteAllUnit() {
        viewModelScope.launch {
            forecastRepositoryDatabase.deleteAllUnit()
        }
    }

}