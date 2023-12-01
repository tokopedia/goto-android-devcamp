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

    private lateinit var newsAdapter: NewsAdapter

    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        setupViews()
        setupRecyclerView()

        observeData()
    }

    private fun setupViews() {
        newsRecyclerView = findViewById(R.id.rv_news)
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        newsRecyclerView.adapter = newsAdapter
        newsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeData() {
        val newsMockData = getMockNewsData(this)
        newsAdapter.insertData(newsMockData.articles)
    }

}