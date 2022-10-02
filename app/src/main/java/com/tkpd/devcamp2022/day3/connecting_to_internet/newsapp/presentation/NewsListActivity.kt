package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkpd.devcamp2022.databinding.ActivityNewsListBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.ApiClient
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.NewsResponse
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.utils.Mapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListActivity : AppCompatActivity() {

    companion object {
        private const val ID_COUNTRY_INDONESIA = "id"
        private const val API_KEY = "ec91e485fad444a8a2678a4d2c6787bb"
    }

    private lateinit var binding: ActivityNewsListBinding
    private lateinit var newsAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initList()
        getNewsData()
    }

    private fun initBinding() {
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initList() {
        newsAdapter = NewsListAdapter()

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            this.adapter = newsAdapter
        }
    }

    private fun getNewsData() {
        ApiClient.create().getNews(ID_COUNTRY_INDONESIA, API_KEY)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.body() != null) {
                        val newsResponse = response.body() as NewsResponse
                        val results = newsResponse.articles
                        if (results != null) {
                            showLoading(false)
                            val getNews = Mapper.toNewsObject(results)
                            newsAdapter.setNews(getNews)
                        }
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {}
            })
    }

    private fun showLoading(showLoading: Boolean) {
        if (showLoading) {
            binding.incLoading.svLoading.startShimmer()
            binding.incLoading.svLoading.visibility = View.VISIBLE
        } else {
            binding.incLoading.svLoading.stopShimmer()
            binding.incLoading.svLoading.visibility = View.GONE
        }
    }
}