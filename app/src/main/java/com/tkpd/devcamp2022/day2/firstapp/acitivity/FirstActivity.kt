package com.tkpd.devcamp2022.day2.firstapp.acitivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.ActivityFirstBinding
import com.tkpd.devcamp2022.day2.firstapp.data.User
import com.tkpd.devcamp2022.day2.firstapp.fragments.FirstFragment

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

        binding.btnNavigateWithDeeplink.setOnClickListener {
            val uri = Uri.parse("example://firstapp?name=testnama&age=23")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.btnNavigateToFragment.setOnClickListener {
            supportFragmentManager.commit {
                add(R.id.container, FirstFragment::class.java, null)
                addToBackStack(null)
            }
        }
    }


}