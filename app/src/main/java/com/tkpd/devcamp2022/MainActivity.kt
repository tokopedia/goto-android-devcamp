package com.tkpd.devcamp2022

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.databinding.ActivityMainBinding
import com.tkpd.devcamp2022.day2.recyclerview.presentation.HomeActivity
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.presentation.WeatherActivity
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.MvvmLiveDataCoroutineActivity
import com.tkpd.devcamp2022.day4.custom_view_animation.AnimationViewActivity
import com.tkpd.devcamp2022.day4.custom_view_animation.CustomViewActivity
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.ui.ContactBookActivity

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDay2RecyclerView.setOnClickListener {
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }

        binding.btnDay3.setOnClickListener {
            startActivity(Intent(this@MainActivity, WeatherActivity::class.java))
        }

        binding.btnDay3Mvvm.setOnClickListener {
            startActivity(Intent(this@MainActivity, MvvmLiveDataCoroutineActivity::class.java))
        }

        binding.btnDay4Customview.setOnClickListener {
            startActivity(Intent(this@MainActivity, CustomViewActivity::class.java))
        }

        binding.btnDay4Animation.setOnClickListener {
            startActivity(Intent(this@MainActivity, AnimationViewActivity::class.java))
        }

        binding.btnDay4.setOnClickListener {
            startActivity(Intent(this@MainActivity, ContactBookActivity::class.java))
        }
    }
}