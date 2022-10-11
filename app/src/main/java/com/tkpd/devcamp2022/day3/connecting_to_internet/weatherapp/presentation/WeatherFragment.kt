package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuckerteam.chucker.api.Chucker
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.FragmentWeatherBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain.WeatherService
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.DEFAULT_LAT
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.DEFAULT_LON
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil.clickWithDebounce
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil.isNetworkConnected

class WeatherFragment: Fragment() {
    private lateinit var weatherViewModel:WeatherViewModel
    private var binding: FragmentWeatherBinding? = null
    private var hasLoaded = false
    private lateinit var forecastItemAdapter: ForecastItemAdapter

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
        prepareLayout()
        observeWeather()
        observeForecast()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { context ->
            WeatherService.getInstance(context)?.let {
                val viewModelFactory = WeatherViewModelFactory(it)
                weatherViewModel = ViewModelProvider(this, viewModelFactory)[WeatherViewModel(it)::class.java]
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (!hasLoaded) {
            hasLoaded = true
            loadWeatherAndForecast()
        }
    }

    private fun prepareLayout() {
        binding?.iconRefresh?.clickWithDebounce {
                loadWeatherAndForecast()
            }

        binding?.iconSettings?.setOnClickListener {
            if (Chucker.isOp) {
                startActivity(activity?.let { it1 -> Chucker.getLaunchIntent(it1) })
            }
        }

        val llManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        forecastItemAdapter = ForecastItemAdapter()
        binding?.run {
            rvForecast.apply {
                layoutManager = llManager
                adapter = forecastItemAdapter
            }
        }
        forecastItemAdapter.clearList()
    }

    private fun loadWeatherAndForecast() {
        if (context?.isNetworkConnected == true) {
            weatherViewModel.loadWeather(DEFAULT_LAT, DEFAULT_LON)
            weatherViewModel.loadForecast(DEFAULT_LAT, DEFAULT_LON)
        } else {
            Toast.makeText(context, resources.getString(R.string.no_connection), Toast.LENGTH_LONG).show()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun observeWeather() {
        weatherViewModel.weatherResult.observe(viewLifecycleOwner) { result ->
            binding?.run {
                derajat.text = result.main.temp.toString() + " \u00B0"
                labelLocation.text = result.name
            }
        }
    }

    private fun observeForecast() {
        weatherViewModel.forecastResult.observe(viewLifecycleOwner) { result ->
            for (i in 1..4) {
                forecastItemAdapter.addList(result.list[i])
                if (i == 4) {
                    forecastItemAdapter.notifyAdapter()
                }
            }
        }
    }
}