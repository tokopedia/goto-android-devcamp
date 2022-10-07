package com.tkpd.devcamp2022.day2.firstapp.acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.firstapp.data.User

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intentdata = intent.data
        if (intent.data == null) {
            val userData = intent.getParcelableExtra<User>(EXTRA_PARCEL)

            if (userData == null) {
                val name = intent.getStringExtra(EXTRA_NAME) ?: "NAMA KOSONG"
                val age = intent.getIntExtra(EXTRA_AGE, 0)

                initView(name, age)
            } else {
                initView(userData.name, userData.age)
            }
        } else {
            val name = intentdata?.getQueryParameter(EXTRA_NAME) ?: "empty from deeplink"
            val age = intentdata?.getQueryParameter(EXTRA_AGE) ?: "0"
            initView(name, age.toInt())
        }

    }

    private fun initView(name: String, age: Int) {
        findViewById<AppCompatTextView>(R.id.txt_name).text = resources.getString(R.string.txt_name, name)
        findViewById<AppCompatTextView>(R.id.txt_age).text = resources.getString(R.string.txt_age, age)
    }

    companion object{
        const val EXTRA_NAME = "name"
        const val EXTRA_AGE = "age"
        const val EXTRA_PARCEL = "extra_parcel"
    }
}