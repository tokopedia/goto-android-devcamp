package com.tkpd.devcamp2022.day4.custom_view_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tkpd.devcamp2022.databinding.ActivityCustomViewAnimationDetailBinding
import com.tkpd.devcamp2022.day4.custom_view_animation.AnimationViewActivity.Companion.EXTRA_DETAIL
import com.tkpd.devcamp2022.day4.custom_view_animation.AnimationViewActivity.Companion.EXTRA_NAME

class CustomViewAnimationDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityCustomViewAnimationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewAnimationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.extras?.getString(EXTRA_NAME)
        val description = intent.extras?.getString(EXTRA_DETAIL)

        binding.tvName.text = name
        binding.tvDescription.text = description
    }
}