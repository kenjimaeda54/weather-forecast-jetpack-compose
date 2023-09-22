package com.example.weatherforecast.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.DataOrExpection
import com.example.weatherforecast.model.Forecast
import com.example.weatherforecast.repositories.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val forecastRepository: ForecastRepository): ViewModel() {
    val data: MutableState<DataOrExpection<Forecast,Boolean,Exception>> = mutableStateOf(
        DataOrExpection(null,true,Exception(""))
    )

     fun getForecast(city: String) {
        viewModelScope.launch {
            data.value.loading = true
            data.value = forecastRepository.getForecast(city)
            if (data.value.toString().isNotEmpty()) data.value.loading = false
        }
    }

}