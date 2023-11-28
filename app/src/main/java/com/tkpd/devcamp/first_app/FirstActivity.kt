package com.tkpd.devcamp.first_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp.R

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }
}