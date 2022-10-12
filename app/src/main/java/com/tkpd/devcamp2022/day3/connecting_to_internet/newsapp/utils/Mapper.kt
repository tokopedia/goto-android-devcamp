package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.ArticlesItem
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.News
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.NewsResponse

object Mapper {

    fun toNewsObject(results: List<ArticlesItem?>): MutableList<News> {
        val listNews = mutableListOf<News>()
        results.forEach { news ->
            val getNews = News(
                news?.title,
                news?.source?.name,
                news?.author,
                news?.url,
                news?.urlToImage
            )
            listNews.add(getNews)
        }
        return listNews
    }

    fun stringToNewsResponse(data: String?): MutableList<News> {
        val type = object : TypeToken<NewsResponse?>() {}.type
        val dataNews = Gson().fromJson<NewsResponse>(data, type)
        val newsObject = Mapper.toNewsObject(dataNews.articles ?: listOf())
        return newsObject?.toMutableList() ?: mutableListOf()
    }
}
