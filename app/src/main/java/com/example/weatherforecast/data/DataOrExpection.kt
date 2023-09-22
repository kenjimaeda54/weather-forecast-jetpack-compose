package com.example.weatherforecast.data

data class DataOrExpection<T,Bool,E: Exception>(
    var data: T? = null,
    var loading: Bool? = null,
    var expection: E? = null,
)