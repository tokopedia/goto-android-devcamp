package com.tkpd.devcamp.practice.presentation.activity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp.R
import com.tkpd.devcamp.databinding.ActivitySearchNewsBinding
import com.tkpd.devcamp.practice.di.DependencyProvider
import com.tkpd.devcamp.practice.domain.model.NewsArticle
import com.tkpd.devcamp.practice.presentation.adapter.SearchNewsAdapter
import com.tkpd.devcamp.practice.presentation.uimodel.SearchNewsState
import com.tkpd.devcamp.practice.presentation.viewmodel.SearchNewsViewModel
import java.lang.Exception

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
class SearchNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchNewsBinding

    /** TODO: Fix this ViewModel initialization, please use our ViewModelFactory as well */
    private val viewModel = SearchNewsViewModel(DependencyProvider.provideNewsRepository())

    private val adapter = SearchNewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TODO("Use ViewBinding")
        setContentView(R.layout.activity_search_news)

        setupView()
        setupObserver()
    }

    private fun setupView() {
        binding.rvNews.adapter = adapter
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            hideSoftKeyboard()

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val keyword = binding.etSearch.text.toString()
                viewModel.searchNews(keyword)

                return@setOnEditorActionListener true
            }

            false
        }

        TODO("set onRefresh here & call viewModel.searchNews() without argument")
    }

    private fun setupObserver() {
        TODO("Observe searchNewsState here, please use the existing function below to handle the state")
    }

    private fun handleSearchNewsState(state: SearchNewsState) {
        when (state) {
            is SearchNewsState.Loading -> handleLoadingState()
            is SearchNewsState.Success -> handleSuccessState(state.list)
            is SearchNewsState.Error -> handleErrorState(state.exception)
        }
    }

    private fun handleLoadingState() {
        binding.loading.visibility = if (binding.swipeRefresh.isRefreshing)
            View.GONE
        else
            View.VISIBLE
        binding.error.visibility = View.GONE
        binding.rvNews.visibility = View.GONE
    }

    private fun handleSuccessState(newsArticles: List<NewsArticle>) {
        binding.rvNews.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
        binding.error.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false

        TODO("Update data to RecyclerView")
    }

    private fun handleErrorState(e: Exception) {
        binding.error.visibility = View.VISIBLE
        binding.error.text = e.message
        binding.loading.visibility = View.GONE
        binding.rvNews.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
    }

    private fun hideSoftKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}