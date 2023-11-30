package com.tkpd.devcamp.customview_bottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        openBottomSheetDetail(
            News(
                "On the eve of Tesla's next major release, Elon Musk gave a bizarre, long-winded interview about everything but the Cybertruck",
                "Zac Johnson",
                "businessinsider.com",
                "https://biztoc.com/x/c191194496ce4396",
                "https://c.biztoc.com/p/c191194496ce4396/s.webp"
            )
        )
    }

    private fun openBottomSheetDetail(news: News) {
        BottomSheetDetailNews.show(supportFragmentManager, news)
    }
}