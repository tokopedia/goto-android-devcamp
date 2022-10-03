package com.tkpd.devcamp2022.day3.room_datastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.ActivityLocalDataBinding
import com.tkpd.devcamp2022.day3.room_datastore.view.fragment.LocalDataFragment

class LocalDataActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLocalDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.local_data_parent_view, LocalDataFragment())
                .commit()
        }
    }
}