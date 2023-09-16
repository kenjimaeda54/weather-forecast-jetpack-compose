package com.example.weatherforecast.navigation
import java.lang.IllegalArgumentException

enum class WeatherScreens {
    SplashScreen,
    MainScreen,
    AboutScreen,
    FavoriteScreen,
    SearchScreen,
    SettingsScreen;
    companion object {
        fun fromRoute(route: String?): WeatherScreens = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            MainScreen.name -> MainScreen
            AboutScreen.name -> AboutScreen
            FavoriteScreen.name -> FavoriteScreen
            SearchScreen.name -> SearchScreen
            SettingsScreen.name -> SettingsScreen
            else -> throw  IllegalArgumentException("Route $route is not regonizable")
        }
    }
}