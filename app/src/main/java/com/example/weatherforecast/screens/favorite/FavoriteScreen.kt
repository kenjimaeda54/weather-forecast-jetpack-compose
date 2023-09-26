package com.example.weatherforecast.screens.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.navigation.WeatherScreens
import com.example.weatherforecast.viewModels.ForecastFavoriteViewModel
import com.example.weatherforecast.views.RowCityFavorite
import com.example.weatherforecast.views.TopBarApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: ForecastFavoriteViewModel = hiltViewModel()
) {
    val list = favoriteViewModel.listFavorite.collectAsState().value

    Scaffold(topBar = {
        TopBarApp(title = "Favorite", navigationIcon = {
            Image(
                modifier = Modifier.clickable { navController.popBackStack() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon"
            )
        })
    }) {
        LazyColumn(
            modifier = Modifier.padding(
                horizontal = it.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr) + it.calculateRightPadding(
                    layoutDirection = LayoutDirection.Rtl
                ) + 15.dp,
                vertical = it.calculateTopPadding() + 10.dp
            )
        ) {
            items(items = list) { favorite ->
                RowCityFavorite(item = favorite, onClickIcon = {
                    favoriteViewModel.deleteOneFavorite(favorite)
                }, onClickRow = {
                    navController.navigate("${WeatherScreens.MainScreen.name}/${favorite.city}")
                })
            }
        }
    }
}