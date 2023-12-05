package com.tkpd.devcamp.practice.data.repository

import com.tkpd.devcamp.practice.data.remote.NewsRemoteDataSource
import com.tkpd.devcamp.practice.domain.model.NewsArticle
import com.tkpd.devcamp.practice.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : NewsRepository {

    override suspend fun searchNews(keyword: String): List<NewsArticle> = withContext(ioDispatcher) {
        TODO("Complete this function")
    }
}