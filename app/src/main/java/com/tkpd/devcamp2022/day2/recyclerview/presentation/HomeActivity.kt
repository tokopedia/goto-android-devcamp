package com.tkpd.devcamp2022.day2.recyclerview.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_container, CustomListViewFragment.newInstance())
                .commitNow()
        }
    }
}