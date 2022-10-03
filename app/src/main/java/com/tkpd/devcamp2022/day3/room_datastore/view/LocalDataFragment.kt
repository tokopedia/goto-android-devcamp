package com.tkpd.devcamp2022.day3.room_datastore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.databinding.FragmentLocalDataBinding

class LocalDataFragment: Fragment() {

    private var binding: FragmentLocalDataBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocalDataBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

}