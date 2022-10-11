package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain.WeatherService

class WeatherViewModelFactory constructor(private val weatherService: WeatherService): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            WeatherViewModel(this.weatherService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}