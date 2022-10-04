package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.ActivityNewsListBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.ApiClient
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.NewsResponse
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.utils.Mapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListActivity : AppCompatActivity() {

    /**
     *
     * Silahkan masukkan Api Key disini ya.
     * di API_KEY
     * replace aja tulisan yang ada
     *
     */
    companion object {
        private const val API_KEY = "2193281732187321"
    }


    private lateinit var binding: ActivityNewsListBinding
    private lateinit var newsAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initList()
        emptyApiKeyChecker()
        getNewsData()
    }

    private fun emptyApiKeyChecker() {
        if (API_KEY == getString(R.string.newsapp_apikey_info)) {
            Toast.makeText(this, getString(R.string.newsapp_apikey_warning), Toast.LENGTH_LONG).show()
            finish()
        }
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
        ApiClient.create().getNews("id", API_KEY)
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