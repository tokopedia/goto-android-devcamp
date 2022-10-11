package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util

object WeatherConsts {
    const val BASE_URL = "https://api.openweathermap.org/"
    const val WEATHER_ENDPOINT = "data/2.5/weather?units=metric"
    const val FORECAST_ENDPOINT = "data/2.5/forecast?units=metric"
    const val API_KEY = "521acc73d9c4e4f4c69c367d44215311"

    // lat long default - Jakarta
    const val DEFAULT_LAT = "-6.175110"
    const val DEFAULT_LON = "106.865036"
}