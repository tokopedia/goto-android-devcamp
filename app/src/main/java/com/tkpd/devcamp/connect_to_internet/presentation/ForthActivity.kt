package com.tkpd.devcamp.connect_to_internet.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp.R
import com.tkpd.devcamp.connect_to_internet.data.FourthRepository
import com.tkpd.devcamp.connect_to_internet.domain.FourthPresenter
import com.tkpd.devcamp.connect_to_internet.domain.FourthPresenterImpl
import com.tkpd.devcamp.connect_to_internet.network.NewsApiRemoteDataSource
import com.tkpd.devcamp.connect_to_internet.network.NewsApiRemoteDataSourceCreator
import com.tkpd.devcamp.connect_to_internet.presentation.adapter.NewsAdapter
import com.tkpd.devcamp.recycler_view.model.Article

interface FourthView {
    fun handleError()
    fun handleSuccess(articles: List<Article>)
    fun showLoading()

}

class ForthActivity : AppCompatActivity(), FourthView {
    private lateinit var presenter: FourthPresenter
    private val newsAdapter: NewsAdapter = NewsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forth)
        /**
         * This should be done via dependency injection or Singleton to make sure its created once
         **/
        val newsApiRemoteDataSource = NewsApiRemoteDataSourceCreator
            .createRetrofit()
            .create(NewsApiRemoteDataSource::class.java)
        /**
         * Following MVP design pattern - Model View Presenter
         * MVVM will be explained later by mas @yuzwan
         **/
        presenter = FourthPresenterImpl(this, FourthRepository(newsApiRemoteDataSource))
        presenter.getTopHeadlines()
    }

    override fun showLoading() {
//        findViewById<TextView>(R.id.title).text = "Loading nih cuy"
    }

    override fun handleError() {
//        findViewById<TextView>(R.id.title).text = "Errrrorrrr"
    }

    override fun handleSuccess(articles: List<com.tkpd.devcamp.recycler_view.model.Article>) {
        with(findViewById<RecyclerView>(R.id.rv_news)) {
            adapter = newsAdapter
            newsAdapter.submitList(articles)
        }
    }

}