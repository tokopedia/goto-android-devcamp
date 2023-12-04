package com.tkpd.devcamp.viewmodel_livedata

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp.R
import com.tkpd.devcamp.connect_to_internet.presentation.FourthView
import com.tkpd.devcamp.connect_to_internet.presentation.adapter.NewsAdapter
import com.tkpd.devcamp.recycler_view.model.Article

class FifthActivity : AppCompatActivity(), FourthView {

    //TODO: Instantiate viewModel variable
    private val viewModel by viewModels<FifthViewModel>()

    private val newsAdapter: NewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forth)

        //TODO: Call getTopHeadLines() function

        //TODO: Observe data from LiveData Observer to UI
    }

    override fun showLoading() {
        findViewById<TextView>(R.id.error).visibility = View.GONE
        findViewById<ProgressBar>(R.id.loading).visibility = View.VISIBLE
    }

    override fun handleError() {
        findViewById<TextView>(R.id.error).visibility = View.VISIBLE
        findViewById<ProgressBar>(R.id.loading).visibility = View.GONE

    }

    override fun handleSuccess(articles: List<Article>) {
        findViewById<ProgressBar>(R.id.loading).visibility = View.GONE
        with(findViewById<RecyclerView>(R.id.rv_news)) {
            adapter = newsAdapter
            newsAdapter.submitList(articles)
        }
    }
}