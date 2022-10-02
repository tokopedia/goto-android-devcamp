package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//  TODO Connecting to Internet - NewsApp - step 3 - create ApiClient

object ApiClient {

    private const val API_BASE = "https://newsapi.org/"
    fun create(): ApiServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiServices::class.java)
    }
}