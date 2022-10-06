package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation.flow

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.databinding.ForecastCardItemBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherForecast
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil.toPrettyString

class ForecastFlowItemViewHolder(private val binding: ForecastCardItemBinding): RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: WeatherForecast.Forecast) {
        WeatherUtil.setWeatherIcon(itemView.context, binding.imageWeather, item.weatherId)
        binding.labelDerajat.text = item.temp.toString() + " \u00B0"
        binding.labelMinDerajat.text = kotlin.math.ceil(item.minTemp).toPrettyString() + " \u00B0"
        binding.labelMaxDerajat.text = kotlin.math.ceil(item.maxTemp).toPrettyString() + " \u00B0"
    }
}