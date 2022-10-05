package com.tkpd.devcamp2022.day3.room_datastore.api.userlist

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserListApiClient {

    private const val API_BASE = "https://reqres.in/"
    fun create(): UserListApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UserListApiService::class.java)
    }
}