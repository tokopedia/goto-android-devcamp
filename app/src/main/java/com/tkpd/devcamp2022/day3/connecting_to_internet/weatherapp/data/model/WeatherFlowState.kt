package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.model

import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherForecast

sealed class WeatherFlowState {
    data class Success(val data: WeatherForecast) : WeatherFlowState()
    data class Failed(val throwable: Throwable?) : WeatherFlowState()
    object Loading : WeatherFlowState()
}
