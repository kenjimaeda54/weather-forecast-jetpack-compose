package com.example.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecast.screens.main.MainFragment
import com.example.weatherforecast.screens.search.SearchScreen
import com.example.weatherforecast.screens.splash.SplashScreen
import com.example.weatherforecast.viewModels.ForecastViewModel


@Composable
fun WeatherRoutes() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController)
        }

        composable("${WeatherScreens.MainScreen.name}/{city}", arguments = listOf(
            navArgument("city") { type = NavType.StringType }
        )) {
            //https://developer.android.com/jetpack/compose/libraries?hl=pt-br#hilt-navigation
            val mainViewModel = hiltViewModel<ForecastViewModel>()
            MainFragment(navController = navController, mainViewModel, city = it.arguments?.getString("city"))
        }

        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

    }

}