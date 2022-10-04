package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.ActivityNewsListBinding
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.News
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.utils.CommonUtils
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.utils.Mapper

class NewsListActivity : AppCompatActivity() {

    /**
     *
     * Silahkan masukkan Api Key disini ya.
     * di API_KEY
     * replace aja tulisan yang ada
     *
     */
    companion object {
        private const val API_KEY = "INPUT-YOUR-NEWS-API-KEY-HERE"
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
        if (API_KEY == "INPUT-YOUR-NEWS-API-KEY-HERE") {
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
        //  TODO Connecting to Internet - NewsApp - step 5 - update to hit endpoint
        //  TODO Connecting to Internet - NewsApp - step 6 - make sure still update ui

        updateUI(getNewsByJsonLocal())
    }

    private fun updateUI(news: List<News>) {
        newsAdapter.setNews(news)
        showLoading(false)

    }

    private fun getNewsByJsonLocal(): MutableList<News> {
        val data = CommonUtils.getAssetJsonData(this, "newsdata.json")
        return Mapper.stringToNewsResponse(data)
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