package com.tkpd.devcamp2022.day2.recyclerview.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.factory.HomeFactory
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.CustomListViewAdapter

class SimpleListViewFragment : Fragment() {

    companion object {
        fun newInstance() = SimpleListViewFragment()
    }

    private val repository = HomeFactory.getRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple_listview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.simple_listview) // ListView Component

        val dataSource = repository.getProducts(1) // data source

        val customAdapter = CustomListViewAdapter(dataSource)
        listView.adapter = customAdapter // set adapter to listview
    }
}