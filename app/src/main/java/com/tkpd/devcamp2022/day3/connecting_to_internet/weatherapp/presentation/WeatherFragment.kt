package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tkpd.devcamp2022.databinding.FragmentWeatherBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain.WeatherService
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.DEFAULT_LAT
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.DEFAULT_LON

class WeatherFragment: Fragment() {
    private lateinit var weatherViewModel:WeatherViewModel
    private var binding: FragmentWeatherBinding? = null
    private var hasLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeWeather()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WeatherService.getInstance()?.let {
            val viewModelFactory = WeatherViewModelFactory(it)
            weatherViewModel = ViewModelProvider(this, viewModelFactory)[WeatherViewModel(it)::class.java]
        }
    }

    override fun onStart() {
        super.onStart()
        if (!hasLoaded) {
            hasLoaded = true
            weatherViewModel.loadWeather(DEFAULT_LAT, DEFAULT_LON)
        }
    }

    private fun observeWeather() {
        weatherViewModel.weatherResult.observe(viewLifecycleOwner) { result ->
            println("++ result = $result")
        }
    }
}