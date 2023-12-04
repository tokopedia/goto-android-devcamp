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

    /** TODO: Declare object that stores all view reference */
    private lateinit var binding: ActivitySixthBinding

    private val viewModel by viewModels<SixthViewModel>()

    private val newsAdapter: NewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** TODO: Initialize the object with inflate() */
        binding = ActivitySixthBinding.inflate(layoutInflater)
        /** TODO: Set the activity content view with view binding root */
        setContentView(binding.root)

        val newsApiRemoteDataSource = NewsApiRemoteDataSourceCreator
            .createRetrofit()
            .create(NewsApiRemoteDataSource::class.java)

        viewModel.getTopHeadline()
        viewModel.setupRepository(FourthRepository(newsApiRemoteDataSource))

        viewModel.state.observe(this) {
            when(it) {
                is ArticleScreenState.Error -> handleError()
                is ArticleScreenState.Loading -> showLoading()
                is ArticleScreenState.Success -> handleSuccess(it.list)
            }
        }
    }

    private fun showLoading() {
        /** TODO: Access all your view with binding */
        binding.error.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
    }

    private fun handleError() {
        binding.error.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
    }

    private fun handleSuccess(articles: List<Article>) {
        binding.loading.visibility = View.GONE
        with(binding.rvNews) {
            adapter = newsAdapter
            newsAdapter.submitList(articles)
        }
    }
}