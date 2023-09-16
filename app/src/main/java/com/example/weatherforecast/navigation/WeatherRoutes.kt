package com.example.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.screens.SplashScreen

@Composable
fun WeatherRoutes()  {
    val navController = rememberNavController()
    NavHost(navController = navController,  startDestination = WeatherScreens.SplashScreen.name ) { 
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen()
        }
    }

}