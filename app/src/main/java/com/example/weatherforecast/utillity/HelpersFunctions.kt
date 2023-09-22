package com.example.weatherforecast.utillity

import android.annotation.SuppressLint
import java.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat")
fun formatterTimeStampToDate(time: Int): String {
    val simpleDateFormat = SimpleDateFormat("E, dd MMM") // Thursday, September 21
    return simpleDateFormat.format(time * 1000L)
}

@SuppressLint("SimpleDateFormat")
fun  formatterTimeStampToHours(time: Int): String{
    val simpleDateFormat = SimpleDateFormat("h:m a") // Thursday, September 21
    return simpleDateFormat.format(time * 1000L)
}

fun formatDecimal(num: Double): String {
   return "%.0f".format(num)
}