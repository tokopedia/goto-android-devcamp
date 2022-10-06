package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.model.WeatherFlowState
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherForecast
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain.WeatherService
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.API_KEY
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherFlowViewModel constructor(private val weatherService: WeatherService) : ViewModel() {

    private val _weatherForecast: MutableStateFlow<WeatherFlowState> =
        MutableStateFlow(WeatherFlowState.Loading)
    val weatherForecast: StateFlow<WeatherFlowState> = _weatherForecast.asStateFlow()

    fun loadWeatherAndForecast(lat: String, lon: String) {
        viewModelScope.launch {
            _weatherForecast.emit(WeatherFlowState.Loading)
            val weatherFlow = flowOf(weatherService.getWeather(lat, lon, API_KEY))
            val forecastFlow = flowOf(weatherService.getForecast(lat, lon, API_KEY))

            val listForecast = arrayListOf<WeatherForecast.Forecast>()
            weatherFlow.zip(forecastFlow) { weather, forecast ->
                forecast.list.forEach { item ->
                    listForecast.add(
                        WeatherForecast.Forecast(
                            weatherId = item.weather[0].id,
                            temp = item.main.temp,
                            minTemp = item.main.tempMin,
                            maxTemp = item.main.tempMax
                        )
                    )
                }
                WeatherForecast(
                    location = weather.name,
                    temp = weather.main.temp,
                    listForecast = listForecast

                ) }.collect {
                _weatherForecast.emit(WeatherFlowState.Success(it))
            }
        }
    }
}