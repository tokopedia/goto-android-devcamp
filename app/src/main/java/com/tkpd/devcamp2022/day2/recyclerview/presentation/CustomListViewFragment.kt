package com.tkpd.devcamp2022.day2.recyclerview.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.factory.HomeFactory
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.sample.CustomAdapter
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel


class CustomListViewFragment : Fragment() {

    companion object {
        fun newInstance() = CustomListViewFragment()
    }

    private val homeRepository = HomeFactory.getRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_listview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = homeRepository.getProducts(10, 0) as  List<ProductUiModel.Item>

        val listView = view.findViewById<ListView>(R.id.home_listview)
        val customAdapter = CustomAdapter(requireContext(), productList)
        listView.adapter = customAdapter

//        val simpleList = resources.getStringArray(R.array.array_of_string)
//        val adapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            android.R.id.text1,
//            simpleList
//        )
//        listView.adapter = adapter
    }
}