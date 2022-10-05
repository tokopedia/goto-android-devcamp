package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.utils

import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.ArticlesItem
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.News

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
}
