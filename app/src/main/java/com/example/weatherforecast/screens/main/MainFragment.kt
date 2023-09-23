package com.example.weatherforecast.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.weatherforecast.viewModels.ForecastViewModel


@Composable
fun MainFragment(navController: NavController, forecastViewModel: ForecastViewModel, city: String?) {
    val forecasts = forecastViewModel.data.value.data

    LaunchedEffect(key1 = true, block = {
        forecastViewModel.getForecast(city.toString())
    })

    if (forecastViewModel.data.value.loading == true) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }

    } else if (forecasts != null) {
        MainScreen(navController = navController, forecasts)
    }

}