package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.domain

import com.google.gson.GsonBuilder
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response.WeatherResponse
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.BASE_URL
import com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util.WeatherConsts.WEATHER_ENDPOINT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET(WEATHER_ENDPOINT)
    suspend fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") appId: String): WeatherResponse

    companion object {
        var weatherService: WeatherService? = null
        fun getInstance() : WeatherService? {
            if (weatherService == null) {
                val gson = GsonBuilder().setLenient().create()

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                weatherService = retrofit.create(WeatherService::class.java)
            }
            return weatherService
        }

    }
}