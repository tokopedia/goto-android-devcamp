package com.tkpd.devcamp.practice.di

import com.tkpd.devcamp.practice.data.remote.NewsRemoteDataSource
import com.tkpd.devcamp.practice.data.repository.NewsRepositoryImpl
import com.tkpd.devcamp.practice.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
object DependencyProvider {

    fun provideNewsRepository(): NewsRepository {
        return NewsRepositoryImpl(
            newsRemoteDataSource = provideNewsRemoteDataSource(),
            ioDispatcher = Dispatchers.IO
        )
    }

    fun provideNewsRemoteDataSource(): NewsRemoteDataSource {
        TODO("Create NewsRemoteDataSource here, use BuildConfig.NEWSAPI_BASE_URL for base url")
    }
}