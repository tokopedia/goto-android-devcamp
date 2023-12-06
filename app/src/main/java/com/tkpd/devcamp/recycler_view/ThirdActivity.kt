package com.tkpd.devcamp.recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.tkpd.devcamp.R
import com.tkpd.devcamp.recycler_view.adapter.NewsAdapter
import com.tkpd.devcamp.recycler_view.data.getMockNewsData

class ThirdActivity : AppCompatActivity() {

    /*
    TODO("initialize your adapter and views")
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        setupViews()
        setupRecyclerView()

        observeData()
    }

    private fun setupViews() {
//        TODO("initialize your recyclerview")
    }

    private fun setupRecyclerView() {
//        TODO("setup your recyclerview")
    }

    private fun observeData() {
        val newsMockData = getMockNewsData(this)
//        TODO("insert your data into recyclerview adapter")
    }

}