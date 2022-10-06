package com.tkpd.devcamp2022.day2.firstapp.acitivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.databinding.ActivityFirstBinding
import com.tkpd.devcamp2022.day2.firstapp.data.User

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNavigateToActivity.setOnClickListener {
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.btnNavigateToActivityWithData.setOnClickListener {
            //val user = User("Nama Test", 31)
            val intent = Intent(this@FirstActivity, SecondActivity::class.java).also {
                it.putExtra(SecondActivity.EXTRA_NAME, "Test Nama")
                it.putExtra(SecondActivity.EXTRA_AGE, 20)
                //it.putExtra(SecondActivity.EXTRA_PARCEL, user)
            }
            startActivity(intent)
        }

        binding.btnNavigateToFragment.setOnClickListener {

        }
    }


}