package com.tkpd.devcamp.practice_binding

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp.connect_to_internet.data.FourthRepository
import com.tkpd.devcamp.connect_to_internet.network.NewsApiRemoteDataSource
import com.tkpd.devcamp.connect_to_internet.network.NewsApiRemoteDataSourceCreator
import com.tkpd.devcamp.connect_to_internet.presentation.adapter.NewsAdapter
import com.tkpd.devcamp.databinding.ActivitySixthBinding
import com.tkpd.devcamp.recycler_view.model.Article
import com.tkpd.devcamp.viewmodel_livedata.state.ArticleScreenState

class SixthActivity : AppCompatActivity() {

    /** [ViewBinding] TODO: Declare object that stores all view reference */
    private lateinit var binding: ActivitySixthBinding

    private val viewModel by viewModels<SixthViewModel>()

    private val newsAdapter: NewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** [ViewBinding] TODO: Initialize the object with inflate() */
        binding = ActivitySixthBinding.inflate(layoutInflater)
        /** [ViewBinding] TODO: Set the activity content view with view binding root */
        setContentView(binding.root)

        setupView()
        setupObserver()

        viewModel.getTopHeadline()
    }

    private fun setupView() {
        val newsApiRemoteDataSource = NewsApiRemoteDataSourceCreator
            .createRetrofit()
            .create(NewsApiRemoteDataSource::class.java)

        viewModel.setupRepository(FourthRepository(newsApiRemoteDataSource))

        /** [SwipeToRefresh] TODO: Set onRefreshListener */
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getTopHeadline()
        }
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
        /** [ViewBinding] TODO: Access all your view with binding */
        binding.error.visibility = View.GONE
        binding.loading.visibility = if (binding.swipeRefresh.isRefreshing) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun handleError() {
        binding.error.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
        binding.rvNews.visibility = View.GONE

        /** [SwipeToRefresh] TODO: Remove refreshing indicator when it's not needed anymore */
        binding.swipeRefresh.isRefreshing = false
    }

    private fun handleSuccess(articles: List<Article>) {
        binding.error.visibility = View.GONE
        binding.loading.visibility = View.GONE
        binding.rvNews.visibility = View.VISIBLE

        /** [SwipeToRefresh] TODO: Remove refreshing indicator when it's not needed anymore */
        binding.swipeRefresh.isRefreshing = false

        with(binding.rvNews) {
            adapter = newsAdapter
            newsAdapter.submitList(articles)
        }
    }
}