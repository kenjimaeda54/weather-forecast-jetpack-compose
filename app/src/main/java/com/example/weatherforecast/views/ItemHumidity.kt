package com.example.weatherforecast.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.R

@Composable
fun RowItemWeather(imageInt: Int, value: String, inverterRow: Boolean = false) {
    Row(verticalAlignment = Alignment.Bottom) {
        if (inverterRow) {
            Text(
                modifier = Modifier.padding(horizontal = 3.dp),
                text = value,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Image(
                modifier = Modifier
                    .size(25.dp),
                painter = painterResource(id = imageInt),
                contentDescription = "Image Item Humidity",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
        } else {
            Image(
                modifier = Modifier
                    .size(25.dp)
                    .padding(horizontal = 3.dp),
                painter = painterResource(id = imageInt),
                contentDescription = "Image Item Humidity",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(text = value, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ItemHumidityPreview() {
    RowItemWeather(imageInt = R.drawable.humidity, value = "63%")
}