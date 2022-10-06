package com.tkpd.devcamp2022.day2.recyclerview.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.sample.CustomAdapter
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

class CustomListViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = mutableListOf<ProductUiModel.Item>()

        val listView = view.findViewById<ListView>(R.id.home_listview)
        val customAdapter = CustomAdapter(requireContext(), productList)
        listView.adapter = customAdapter
    }
}