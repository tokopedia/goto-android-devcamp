package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.databinding.ForecastCardItemBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.ForecastResponse
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherUtil.toPrettyString

class ForecastItemViewHolder(private val binding: ForecastCardItemBinding): RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(listItem: ForecastResponse.ListItem) {
        if (listItem.weather.isNotEmpty()) {
            WeatherUtil.setWeatherIcon(itemView.context, binding.imageWeather, listItem.weather[0].id)
        }

        binding.labelDerajat.text = listItem.main.temp.toString() + " \u00B0"
        binding.labelMinDerajat.text = kotlin.math.ceil(listItem.main.tempMin).toPrettyString() + " \u00B0"
        binding.labelMaxDerajat.text = kotlin.math.ceil(listItem.main.tempMax).toPrettyString() + " \u00B0"
    }
}