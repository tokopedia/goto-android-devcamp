package com.tkpd.devcamp2022.day3.room_datastore.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.ActivityCommonLocalDataBinding
import com.tkpd.devcamp2022.day3.room_datastore.view.fragment.ProtoDataStoreFragment

class ProtoDataStoreActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCommonLocalDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonLocalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.local_data_parent_view, ProtoDataStoreFragment())
                .commit()
        }
    }
}