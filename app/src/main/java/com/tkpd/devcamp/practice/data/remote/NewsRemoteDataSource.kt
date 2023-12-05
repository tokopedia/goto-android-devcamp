package com.tkpd.devcamp.practice.data.remote

import com.tkpd.devcamp.BuildConfig
import com.tkpd.devcamp.practice.domain.dto.SearchNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
interface NewsRemoteDataSource {

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q") keyword: String,
        @Query("apikey") apiKey: String = BuildConfig.NEWSAPI_API_KEY,
    ): SearchNewsResponse
}