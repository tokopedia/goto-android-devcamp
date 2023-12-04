package com.tkpd.devcamp.connect_to_internet.network

import com.tkpd.devcamp.BuildConfig
import com.tkpd.devcamp.connect_to_internet.data.model.NewsApiTopHeadlinesResponse
import com.tkpd.devcamp.connect_to_internet.data.model.NewsApiVoteRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiRemoteDataSourceCreator {
    fun createRetrofit(): Retrofit //TODO section-4 Create News API remote data source
}

interface NewsApiRemoteDataSource {
    //TODO section-4 Create news API Get topHeadlineNews
    //TODO section-4 sample API https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=xxx

    @Headers("Authorization: apiKey")
    @POST("/v2/vote/{news-id}")
    suspend fun sampleOfDynamicPath(
        @Path("path-id") pathId: String,
        @Query("user-id") userId: String,
        @Body body: NewsApiVoteRequest
    )
}
