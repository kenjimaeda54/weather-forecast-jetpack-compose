package com.example.weatherforecast.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.model.FavoritesModel

val item = FavoritesModel(
    city = "Rio de Janeiro",
    country = "BR"
)


@SuppressLint("InvalidColorHexValue")
@Composable
fun RowCityFavorite(item: FavoritesModel, onClickIcon: () -> Unit, onClickRow: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .height(50.dp).clickable { onClickRow() },
        shape = CircleShape.copy(topEnd = CornerSize(3.dp)), color = Color(0xFFFFADD8E6)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.width(150.dp),
                text =
                item.city, style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    color = Color.Black.copy(0.7f)
                )
            )
            Box(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(7.dp),
                        color = Color.Gray.copy(0.5f)
                    ), contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 7.dp),
                    text =
                    item.country, style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp,
                        color = Color.Black.copy(0.7f)
                    )
                )
            }
            IconButton(onClick = onClickIcon) {
                Image(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Icon delete",
                    colorFilter = ColorFilter.tint(
                        Color.Red.copy(0.7f)
                    )
                )
            }
        }
    }


}


@Preview(showBackground = true, backgroundColor = 0xFFF0000)
@Composable
fun RowCityFavoritePreview() {
    RowCityFavorite(item, onClickIcon = {}, onClickRow = {})
}