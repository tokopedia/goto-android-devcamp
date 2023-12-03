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

object NewsApiRemoteDataSourceCreator {
    fun createRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.NEWSAPI_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface NewsApiRemoteDataSource {
    @GET("/v2/top-headlines")
    suspend fun topHeadlineNews(
        @Query("country") country: String,
        @Query("apikey") apiKey: String = BuildConfig.NEWSAPI_API_KEY
    ): NewsApiTopHeadlinesResponse

    @Headers("Authorization: apiKey")
    @POST("/v2/vote/{news-id}")
    suspend fun sampleOfDynamicPath(
        @Path("path-id") pathId: String,
        @Query("user-id") userId: String,
        @Body body: NewsApiVoteRequest
    )
}
