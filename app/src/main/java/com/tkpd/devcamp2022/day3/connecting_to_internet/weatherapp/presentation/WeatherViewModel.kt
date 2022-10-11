package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.ForecastResponse
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherForecast
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherResponse
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain.WeatherService
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class WeatherViewModel constructor(private val weatherService: WeatherService) : ViewModel() {

    private val _weatherResult =
        MutableLiveData<WeatherResponse>()
    val weatherResult: LiveData<WeatherResponse>
        get() = _weatherResult

    private val _forecastResult =
        MutableLiveData<ForecastResponse>()
    val forecastResult: LiveData<ForecastResponse>
        get() = _forecastResult

    fun loadWeather(lat: String, long: String) {
        viewModelScope.launch {
            val infoResponse = weatherService.getWeather(lat, long, API_KEY)
            _weatherResult.postValue(infoResponse)
        }
    }

    fun loadForecast(lat: String, long: String) {
        viewModelScope.launch {
            val forecastResponse = weatherService.getForecast(lat, long, API_KEY)
            _forecastResult.postValue(forecastResponse)
        }
    }
}