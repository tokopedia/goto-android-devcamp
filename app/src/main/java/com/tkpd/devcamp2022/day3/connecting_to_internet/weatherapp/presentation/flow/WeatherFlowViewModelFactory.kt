package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain.WeatherService

class WeatherFlowViewModelFactory constructor(private val weatherService: WeatherService): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherFlowViewModel::class.java)) {
            WeatherFlowViewModel(this.weatherService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}