package com.example.weatherforecast.views

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable() (RowScope.() -> Unit) = {},
    navigationIcon: @Composable () -> Unit = {}
) {
    //existe varios topbar como SmallTopBar,MediumTopBar
    //cada topbar tem suas cores repara na propriedde TopAppBarDefaults.centerAlignedTopAppBarColors
    //e porque o tipo de top bar e center
    CenterAlignedTopAppBar(
        modifier = modifier
            .height(30.dp)
            .padding(vertical = 3.dp),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,

                )
        },
        actions = actions,
        navigationIcon = navigationIcon,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )

}


@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    TopBarApp(title = "Portland")
}