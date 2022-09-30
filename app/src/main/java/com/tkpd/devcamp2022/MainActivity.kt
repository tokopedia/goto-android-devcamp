package com.tkpd.devcamp2022

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.databinding.ActivityMainBinding
import com.tkpd.devcamp2022.day2.recyclerview.presentation.HomeActivity
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation.WeatherActivity
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.ui.ContactBookActivity

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDay2.setOnClickListener {
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }

        binding.btnDay3.setOnClickListener {
            startActivity(Intent(this@MainActivity, WeatherActivity::class.java))
        }

        binding.btnDay4.setOnClickListener {
            startActivity(Intent(this@MainActivity, ContactBookActivity::class.java))
        }
    }
}