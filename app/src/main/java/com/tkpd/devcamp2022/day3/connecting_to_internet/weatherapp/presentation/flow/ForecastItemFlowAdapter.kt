package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.databinding.ForecastCardItemBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherForecast

class ForecastItemFlowAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listForecast = mutableListOf<WeatherForecast.Forecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ForecastCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastFlowItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastFlowItemViewHolder -> {
                holder.bind(listForecast[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return listForecast.size
    }

    fun clearList() {
        listForecast.clear()
    }

    fun addList(item: WeatherForecast.Forecast) {
        listForecast.add(item)
    }

    fun notifyAdapter() {
        notifyDataSetChanged()
    }
}