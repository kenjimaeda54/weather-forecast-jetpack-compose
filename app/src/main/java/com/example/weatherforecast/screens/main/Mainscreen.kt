package com.example.weatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherforecast.R
import com.example.weatherforecast.model.FavoritesModel
import com.example.weatherforecast.model.Forecast
import com.example.weatherforecast.navigation.WeatherScreens
import com.example.weatherforecast.utillity.formatDecimal
import com.example.weatherforecast.utillity.formatterTimeStampToDate
import com.example.weatherforecast.utillity.formatterTimeStampToHours
import com.example.weatherforecast.viewModels.ForecastFavoriteViewModel
import com.example.weatherforecast.views.RowItemWeather
import com.example.weatherforecast.views.RowWeatherDetails
import com.example.weatherforecast.views.TopBarApp


val listItemsDropMenu = listOf<String>("About", "Favorites", "Settings")

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    forecast: Forecast,
    favoriteViewModel: ForecastFavoriteViewModel = hiltViewModel()
) {
    val listWeather = forecast.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${listWeather.weather[0].icon}@2x.png"
    val showDialog = remember {
        mutableStateOf(false)
    } //precisa mandar um mutable state se nao sera considerado imutavel quando passa para uma funcoa mesmo sendo var

    if (showDialog.value) {
        ShowDialogDropMenu(showDialog, navController)
    }

    fun handleClickIconFavorite() {
        val favoritesModel = FavoritesModel(
            city = forecast.city.name,
            country = forecast.city.country
        )
        val haveCity = favoriteViewModel.listFavorite.value.find {
            it.city == forecast.city.name
        }

        if (haveCity == null) {
            favoriteViewModel.insertFavorite(favoritesModel)
        }

    }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 7.dp)
                    .fillMaxWidth(), shadowElevation = 20.dp
            ) {
                TopBarApp(
                    title = "${forecast.city.name}, ${forecast.city.country}", actions = {
                        Image(
                            modifier = Modifier.clickable { navController.navigate(WeatherScreens.SearchScreen.name) },
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Top Bar Icon"
                        )
                        Image(
                            modifier = Modifier.clickable { showDialog.value = true },
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More Vert Top Bar Icon"
                        )
                    }, navigationIcon = {
                        Image(
                            modifier = Modifier
                                .scale(0.8f)
                                .clickable { handleClickIconFavorite() },
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite Icon",
                            contentScale = ContentScale.Fit,
                            colorFilter = ColorFilter.tint(Color.Red.copy(0.8f))
                        )
                    })
            }
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = formatterTimeStampToDate(listWeather.dt),
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Surface(
                modifier = Modifier.size(155.dp), shape = CircleShape, color = Color(0xFFFFC400)
            ) {
                Column(
                    modifier = Modifier.padding(top = 0.dp, bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                            .crossfade(true).build(),
                        modifier = Modifier.size(90.dp),
                        contentDescription = "Icon conditions weather",
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${formatDecimal(listWeather.temp.day)}°",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = listWeather.weather[0].main, style = TextStyle(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                fontStyle = FontStyle.Italic
                            )
                        )
                    }


                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp, start = 20.dp, top = 30.dp, bottom = 15.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    RowItemWeather(
                        imageInt = R.drawable.humidity,
                        value = "${listWeather.humidity} %"
                    )
                    RowItemWeather(
                        imageInt = R.drawable.pressure,
                        value = "${listWeather.pressure} psi"
                    )
                    RowItemWeather(
                        imageInt = R.drawable.wind,
                        value = "${listWeather.pressure} mph"
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    RowItemWeather(
                        imageInt = R.drawable.sunrise,
                        value = formatterTimeStampToHours(listWeather.sunrise)
                    )

                    RowItemWeather(
                        imageInt = R.drawable.sunset,
                        value = formatterTimeStampToHours(listWeather.sunset),
                        inverterRow = true
                    )
                }
            }
            Text(
                modifier = Modifier.padding(bottom = 15.dp),
                text = "This Week", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
            Surface(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                color = Color.Gray,
                shape = RoundedCornerShape(size = 8.dp)
            ) {
                LazyColumn {
                    items(items = forecast.list) { weather ->
                        RowWeatherDetails(item = weather)
                    }
                }
            }
        }
    }
}

@Composable
fun ShowDialogDropMenu(showDialog: MutableState<Boolean>, navController: NavController) {

    //vai alinhar no top ao lado esqeurdo da view por causa da propriedade wrapContentSize
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(expanded = showDialog.value, onDismissRequest = {
            showDialog.value = false
        }) {
            listItemsDropMenu.forEach {
                DropdownMenuItem(text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            imageVector = when (it) {
                                "About" -> Icons.Default.Info
                                "Favorites" -> Icons.Default.FavoriteBorder
                                else -> Icons.Default.Settings
                            }, contentDescription = it, contentScale = ContentScale.Fit
                        )
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = it,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                }, onClick = {
                    showDialog.value = false
                    when (it) {
                        "About" -> navController.navigate(WeatherScreens.AboutScreen.name)
                        "Favorites" -> navController.navigate(WeatherScreens.FavoriteScreen.name)
                        else -> navController.navigate(WeatherScreens.SettingsScreen.name)
                    }
                })
            }
        }
    }

}

