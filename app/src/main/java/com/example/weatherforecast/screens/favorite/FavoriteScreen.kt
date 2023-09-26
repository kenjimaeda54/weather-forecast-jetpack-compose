package com.example.weatherforecast.screens.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.weatherforecast.views.TopBarApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController) {
    Scaffold(topBar = {
        TopBarApp(title = "Favorite", navigationIcon = {
            Image(
                modifier = Modifier.clickable { navController.popBackStack() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon"
            )
        })
    }) {
        Text(modifier = Modifier.padding(it), text = "FavoriteScreen")
    }
}