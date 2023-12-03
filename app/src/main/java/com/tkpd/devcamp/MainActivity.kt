package com.tkpd.devcamp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp.connect_to_internet.presentation.ForthActivity
import com.tkpd.devcamp.customview_bottomsheet.SecondActivity
import com.tkpd.devcamp.databinding.ActivityMainBinding
import com.tkpd.devcamp.first_app.FirstActivity
import com.tkpd.devcamp.practice_binding.SixthActivity
import com.tkpd.devcamp.recycler_view.ThirdActivity
import com.tkpd.devcamp.unit_test.SeventhActivity
import com.tkpd.devcamp.viewmodel_livedata.FifthActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFirstApp.setOnClickListener {
            startActivity(Intent(this@MainActivity, FirstActivity::class.java))
        }

        binding.btnCustomviewBottomsheet.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }

        binding.btnRecyclerView.setOnClickListener {
            startActivity(Intent(this@MainActivity, ThirdActivity::class.java))
        }

        binding.btnConnectToInternet.setOnClickListener {
            startActivity(Intent(this@MainActivity, ForthActivity::class.java))
        }

        binding.btnMvvm.setOnClickListener {
            startActivity(Intent(this@MainActivity, FifthActivity::class.java))
        }

        binding.btnPracticeBinding.setOnClickListener {
            startActivity(Intent(this@MainActivity, SixthActivity::class.java))
        }

        binding.btnUnitTest.setOnClickListener {
            startActivity(Intent(this@MainActivity, SeventhActivity::class.java))
        }
    }
}