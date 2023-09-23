package com.example.weatherforecast.views

import android.annotation.SuppressLint
import android.graphics.ColorSpace.Model
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherforecast.model.FeelsLike
import com.example.weatherforecast.model.List
import com.example.weatherforecast.model.Temp
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.utillity.formatDecimal
import com.example.weatherforecast.utillity.formatterTimeStampToDate
import kotlinx.coroutines.selects.whileSelect
import kotlin.math.min


val list = List(
    clouds = 0,
    deg = 253,
    dt = 1695412800,
    feels_like = FeelsLike(
        day = 295.98,
        night = 289.23,
        eve = 295.23,
        morn = 283.9
    ),
    gust = 3.42,
    humidity = 32,
    pop = 0.0,
    pressure = 1014,
    speed = 2.34,
    sunrise = 1695391051,
    sunset = 1695434969,
    temp = Temp(
        day = 296.73,
        min = 284.75,
        max = 297.64,
        night = 290.26,
        eve = 296.23,
        morn = 284.75
    ),
    weather = listOf(
        Weather(
            id = 800,
            main = "Clear",
            description = "sky is clear",
            icon = "01d"
        )
    )
)

@SuppressLint("InvalidColorHexValue")
@Composable
fun RowWeatherDetails(item: List) {
    val imageUrl = "https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 15.dp),
        shape = CircleShape.copy(topEnd = CornerSize(10.dp)), color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 13.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = formatterTimeStampToDate(item.dt).split(",")[0])
            AsyncImage(
                modifier = Modifier.size(30.dp),
                model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
                    .build(),
                contentDescription = "Icon weather row details",
                contentScale = ContentScale.Fit
            )
            Surface(
                modifier = Modifier.width(120.dp),
                shape = RoundedCornerShape(corner = CornerSize(7.dp)), color = Color(
                    0xFFF0C906
                )
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 9.dp),
                    text = item.weather[0].description,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                )

            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue.copy(alpha = 0.7f)
                    )
                ) {
                    append("${formatDecimal(item.temp.max)}° ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                ) {
                    append("${formatDecimal(item.temp.min)}°")
                }
            })

        }
    }

}


@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF0000)
fun RowWeatherDetailsPreview() {
    RowWeatherDetails(item = list)
}