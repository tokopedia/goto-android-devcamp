package com.tkpd.devcamp.practice.domain.repository

import com.tkpd.devcamp.practice.domain.model.NewsArticle

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
interface NewsRepository {

    suspend fun searchNews(keyword: String): List<NewsArticle>
}