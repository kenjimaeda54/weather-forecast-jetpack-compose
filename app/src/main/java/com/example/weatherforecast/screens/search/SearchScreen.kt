package com.example.weatherforecast.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherforecast.navigation.WeatherScreens
import com.example.weatherforecast.views.TopBarApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current

    //https://developer.android.com/jetpack/compose/state?hl=pt-br#:~:text=Embora%20remember%20ajude%20a%20manter,ser%20salvo%20em%20um%20Bundle%20.
    //ideal uso do remeberSaveable e em textFields
    var searchCity by rememberSaveable {
        mutableStateOf("")
    }

    val validTextField by remember(searchCity) {
        mutableStateOf(searchCity.trim().isNotEmpty())
    }


    Scaffold(topBar = {
        TopBarApp(title = "Search", navigationIcon = {
            Image(
                modifier = Modifier.clickable { navController.popBackStack() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon"
            )
        })
    }) {
        Surface(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 7.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .fillMaxWidth(),
                    value = searchCity,
                    shape = RoundedCornerShape(7.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        if (!validTextField) return@KeyboardActions
                        keyboardController?.hide()
                        navController.navigate("${WeatherScreens.MainScreen.name}/$searchCity")
                        searchCity = ""
                    }),
                    onValueChange = { city ->
                        searchCity = city
                    },
                    label = {
                        Text(
                            text = "City", style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black.copy(0.7f)
                            )
                        )
                    })//propriedade placeholder sera o placeholder comum que encontramos React native e swift, label vai ficar em cima do outline o que colocarmos
            }
        }
    }
}