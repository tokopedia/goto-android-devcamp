package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response


data class WeatherForecast(
    val areaName: String = "",
    val country: String = "",
    val lon: Double = 0.0,
    val lat: Double = 0.0,
    val weatherMain: String = "",
    val weatherDesc: String = "",
    val listForecast: List<Forecast> = emptyList()
) {
    data class Forecast(
        val mainTemp : Double = 0.0,
        val mainWeather: String = "",
        val descWeather: String = ""
    )
}
