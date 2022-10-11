package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation.flow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuckerteam.chucker.api.Chucker
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.FragmentWeatherBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.model.WeatherFlowState
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherForecast
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain.WeatherService
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.DEFAULT_LAT
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.DEFAULT_LON
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil.clickWithDebounce
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil.isNetworkConnected
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WeatherFlowFragment: Fragment() {
    private lateinit var weatherFlowViewModel: WeatherFlowViewModel
    private var binding: FragmentWeatherBinding? = null
    private var hasLoaded = false
    private lateinit var forecastItemFlowAdapter: ForecastItemFlowAdapter

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { context ->
            WeatherService.getInstance(context)?.let {
                val viewModelFlowFactory = WeatherFlowViewModelFactory(it)
                weatherFlowViewModel = ViewModelProvider(this, viewModelFlowFactory)[WeatherFlowViewModel(it)::class.java]
            }
        }
        prepareLayout()
        setupViewModel()
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
        forecastItemFlowAdapter = ForecastItemFlowAdapter()
        binding?.run {
            rvForecast.apply {
                layoutManager = llManager
                adapter = forecastItemFlowAdapter
            }
        }
        forecastItemFlowAdapter.clearList()
    }

    private fun loadWeatherAndForecast() {
        if (context?.isNetworkConnected == true) {
            weatherFlowViewModel.loadWeatherAndForecast(DEFAULT_LAT, DEFAULT_LON)
        } else {
            Toast.makeText(context, resources.getString(R.string.no_connection), Toast.LENGTH_LONG).show()
        }
    }

    private fun setupViewModel() {
        weatherFlowViewModel.weatherForecast.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED).onEach { state ->
            when (state) {
                is WeatherFlowState.Success -> {
                    val data = state.data
                    updateLayout(data)
                }
                is WeatherFlowState.Loading -> {
                    binding?.run {
                        loader.show()
                        rlContent.visibility = View.GONE
                    }
                }
                is WeatherFlowState.Failed -> {
                    Toast.makeText(
                        context,
                        state.throwable?.message ?: "Fail to get data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }.launchIn(lifecycleScope)
    }

    @SuppressLint("SetTextI18n")
    private fun updateLayout(data: WeatherForecast) {
        binding?.run {
            loader.hide()
            rlContent.visibility = View.VISIBLE
            derajat.text = data.temp.toString() + " \u00B0"
            labelLocation.text = data.location
            for (i in 1..4) {
                forecastItemFlowAdapter.addList(data.listForecast[i])
                if (i == 4) {
                    forecastItemFlowAdapter.notifyAdapter()
                }
            }
        }
    }
}