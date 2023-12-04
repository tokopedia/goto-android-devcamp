package com.tkpd.devcamp.practice_binding

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp.R
import com.tkpd.devcamp.connect_to_internet.data.FourthRepository
import com.tkpd.devcamp.connect_to_internet.network.NewsApiRemoteDataSource
import com.tkpd.devcamp.connect_to_internet.network.NewsApiRemoteDataSourceCreator
import com.tkpd.devcamp.connect_to_internet.presentation.adapter.NewsAdapter
import com.tkpd.devcamp.recycler_view.model.Article
import com.tkpd.devcamp.viewmodel_livedata.state.ArticleScreenState

class SixthActivity : AppCompatActivity() {

    private val viewModel by viewModels<SixthViewModel>()

    private val newsAdapter: NewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)

        setupView()
        setupObserver()

        viewModel.getTopHeadline()
    }

    private fun setupView() {
        val newsApiRemoteDataSource = NewsApiRemoteDataSourceCreator
            .createRetrofit()
            .create(NewsApiRemoteDataSource::class.java)

        viewModel.setupRepository(FourthRepository(newsApiRemoteDataSource))
    }

    private fun setupObserver() {
        viewModel.state.observe(this) {
            when(it) {
                is ArticleScreenState.Error -> handleError()
                is ArticleScreenState.Loading -> showLoading()
                is ArticleScreenState.Success -> handleSuccess(it.list)
            }
        }
    }

    private fun showLoading() {
        findViewById<TextView>(R.id.error).visibility = View.GONE
        findViewById<ProgressBar>(R.id.loading).visibility = View.VISIBLE
    }

    private fun handleError() {
        findViewById<TextView>(R.id.error).visibility = View.VISIBLE
        findViewById<ProgressBar>(R.id.loading).visibility = View.GONE
        findViewById<RecyclerView>(R.id.rv_news).visibility = View.GONE
    }

    private fun handleSuccess(articles: List<Article>) {
        findViewById<TextView>(R.id.error).visibility = View.GONE
        findViewById<ProgressBar>(R.id.loading).visibility = View.GONE
        findViewById<RecyclerView>(R.id.rv_news).visibility = View.VISIBLE

        with(findViewById<RecyclerView>(R.id.rv_news)) {
            adapter = newsAdapter
            newsAdapter.submitList(articles)
        }
    }
}