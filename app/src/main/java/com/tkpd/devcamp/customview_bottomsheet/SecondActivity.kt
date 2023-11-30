package com.tkpd.devcamp.customview_bottomsheet

import android.os.Bundle
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.tkpd.devcamp.customview_bottomsheet.customview.NewsCard
import com.tkpd.devcamp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.let {
            // TODO: add custom view to activity programmatically
        }
    }
}