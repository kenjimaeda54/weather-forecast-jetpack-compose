package com.example.weatherforecast.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherforecast.R
import com.example.weatherforecast.model.Forecast
import com.example.weatherforecast.utillity.formatDecimal
import com.example.weatherforecast.utillity.formatterTimeStampToDate
import com.example.weatherforecast.utillity.formatterTimeStampToHours
import com.example.weatherforecast.views.RowItemWeather
import com.example.weatherforecast.views.TopBarApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, forecast: Forecast) {
    val listWeather = forecast.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${listWeather.weather[0].icon}@2x.png"

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
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Top Bar Icon"
                        )
                        Image(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More Vert Top Bar Icon"
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
            
        }
    }
}


//@Composable
//@Preview(showBackground = true)
//fun MainScreenPreview() {
//    MainScreen()
//}