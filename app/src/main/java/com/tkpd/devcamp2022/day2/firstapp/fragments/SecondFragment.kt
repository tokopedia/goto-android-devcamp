package com.tkpd.devcamp2022.day2.firstapp.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.R

class SecondFragment: Fragment(R.layout.fragment_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvTitle = view.findViewById<AppCompatTextView>(R.id.txt_title)

        arguments?.let {
            tvTitle.text = it.getString(EXTRA_DATA) ?: "Data Kosong"
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}