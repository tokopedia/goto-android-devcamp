package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//  TODO Connecting to Internet - NewsApp - step 4 - create ApiService

interface ApiServices {

    @GET("v2/top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

}