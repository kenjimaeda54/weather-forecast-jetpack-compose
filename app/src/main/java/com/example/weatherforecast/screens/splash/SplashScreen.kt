package com.example.weatherforecast.screens.splash

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforecast.R
import com.example.weatherforecast.navigation.WeatherScreens
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(initialValue = 0f)
    }

    //chamado no inicio e do coretine
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.7f, animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator().getInterpolation(it)
            }
        ))
        delay(2000L)
        navController.navigate("${WeatherScreens.MainScreen.name}/portland")
    })

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(330.dp)
                .scale(scale.value)
                .border(
                    border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.2f)),
                    shape = CircleShape
                ), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.sun),
                    contentDescription = "Sun SplashScreen"
                )
                Text(
                    text = "Find the Sun?",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
            }

        }
    }
}

