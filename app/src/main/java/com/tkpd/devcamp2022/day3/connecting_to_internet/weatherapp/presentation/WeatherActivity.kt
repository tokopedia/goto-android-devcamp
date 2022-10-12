package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation.flow.WeatherFlowFragment

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherFlowFragment())
                .commit()
        }
    }
}