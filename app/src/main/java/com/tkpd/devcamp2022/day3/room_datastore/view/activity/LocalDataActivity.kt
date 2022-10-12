package com.tkpd.devcamp2022.day3.room_datastore.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.databinding.ActivityLocalDataBinding

class LocalDataActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLocalDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRoom.setOnClickListener {
            startActivity(Intent(this@LocalDataActivity, RoomActivity::class.java))
        }

        binding.btnPreference.setOnClickListener {
            startActivity(Intent(this@LocalDataActivity, PreferenceDataStoreActivity::class.java))
        }

        binding.btnProto.setOnClickListener {
            startActivity(Intent(this@LocalDataActivity, ProtoDataStoreActivity::class.java))
        }
    }
}