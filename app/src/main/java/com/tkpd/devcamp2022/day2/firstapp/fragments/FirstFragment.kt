package com.tkpd.devcamp2022.day2.firstapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.tkpd.devcamp2022.R

class FirstFragment: Fragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnNavigate = view.findViewById<Button>(R.id.btn_navigate_to_second_fragment)

        btnNavigate.setOnClickListener {
            parentFragmentManager.commit {
                add(R.id.container, SecondFragment::class.java, null)
                addToBackStack(null)
            }
        }
    }
}