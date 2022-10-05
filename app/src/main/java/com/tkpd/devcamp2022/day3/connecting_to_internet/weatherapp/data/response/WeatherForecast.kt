package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response


data class WeatherForecast(
    val temp: Double = 0.0,
    val location: String = "",
    val listForecast: List<Forecast> = emptyList()
) {
    data class Forecast(
        val weatherId: Int = 0,
        val temp : Double = 0.0,
        val minTemp: Double = 0.0,
        val maxTemp: Double = 0.0
    )
}
