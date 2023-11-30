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
            it.addView(
                NewsCard(this).apply {
                    layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                        setMargins(0, 0, 0, 20)
                    }
                }
            )

            it.addView(
                NewsCard(
                    this,
                    newsTitle = "News Title",
                    newsAuthor = "Me & Friends",
                    newsDescription = "Lorem ipsum, testing news card"
                )
            )
        }
    }
}