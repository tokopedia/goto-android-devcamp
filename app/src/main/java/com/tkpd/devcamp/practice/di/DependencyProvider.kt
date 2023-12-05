package com.tkpd.devcamp.practice.di

import com.tkpd.devcamp.BuildConfig
import com.tkpd.devcamp.practice.data.remote.NewsRemoteDataSource
import com.tkpd.devcamp.practice.data.repository.NewsRepositoryImpl
import com.tkpd.devcamp.practice.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NEWSAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsRemoteDataSource::class.java)
    }
}