package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.databinding.FragmentWeatherBinding

class WeatherFragment: Fragment() {

    private var binding: FragmentWeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }
}