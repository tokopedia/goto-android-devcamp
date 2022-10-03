package com.tkpd.devcamp2022.day4.custom_view_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tkpd.devcamp2022.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}