package com.example.weatherforecast.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.model.UnitModel
import com.example.weatherforecast.utillity.UnitsTemperature
import com.example.weatherforecast.viewModels.ForecastViewModel
import com.example.weatherforecast.views.TopBarApp




@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    unitsViewModel: ForecastViewModel = hiltViewModel()
) {

    var celsiusToogle by remember {
        mutableStateOf(true)
    }

    val unitSelected by remember(celsiusToogle) {
        mutableStateOf(if (celsiusToogle) UnitsTemperature.Celsius.units else UnitsTemperature.Fahrenith.units)
    }

    fun handleUnits() {
        unitsViewModel.deleteAllUnit()
        val unit = UnitModel(unit = unitSelected)
        unitsViewModel.insertUnit(unit)
    }

    Scaffold(topBar = {
        TopBarApp(title = "Settings", navigationIcon = {
            Image(
                modifier = Modifier.clickable { navController.popBackStack() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon"
            )
        })
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Change Units of Measurement", style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
            IconToggleButton(
                checked = celsiusToogle,
                onCheckedChange = {
                    celsiusToogle = !celsiusToogle

                },
                modifier = Modifier.fillMaxWidth(0.5f),
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = Color.Magenta,
                    disabledContainerColor = Color.Magenta,
                    checkedContainerColor = Color.Magenta,
                )
            ) {
                Text(
                    text = unitSelected,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )
            }
            Button(modifier = Modifier.clip(CircleShape), colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFA500)
            ), onClick = {
                handleUnits()
            }) {
                Text(modifier = Modifier.padding(all = 5.dp), text = "Save")
            }
        }
    }
}