package com.tkpd.devcamp.connect_to_internet.data

import com.tkpd.devcamp.BuildConfig
import com.tkpd.devcamp.connect_to_internet.data.model.NewsApiTopHeadlinesResponse
import com.tkpd.devcamp.connect_to_internet.network.ApiResult
import com.tkpd.devcamp.connect_to_internet.network.NewsApiRemoteDataSource
import com.tkpd.devcamp.connect_to_internet.network.safeApiCall

class FourthRepository(
    private val remoteDataSource: NewsApiRemoteDataSource
) {
    suspend fun getTopHeadlines() {}//: ApiResult<NewsApiTopHeadlinesResponse> {
        //TODO section-4 calling api
//        return ApiResult.Success(
//            NewsApiTopHeadlinesResponse(
//
//            )
//        )
    //}
}