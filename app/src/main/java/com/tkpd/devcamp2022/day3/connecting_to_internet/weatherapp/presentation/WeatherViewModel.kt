package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherResponse
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain.WeatherService
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.API_KEY
import kotlinx.coroutines.launch

class WeatherViewModel constructor(private val weatherService: WeatherService) : ViewModel() {

    private val _weatherResult =
        MutableLiveData<WeatherResponse>()
    val weatherResult: LiveData<WeatherResponse>
        get() = _weatherResult

    fun loadWeather(lat: String, long: String) {
        viewModelScope.launch {
            val infoResponse = weatherService.getWeather(lat, long, API_KEY)
            _weatherResult.postValue(infoResponse)
        }
    }
}