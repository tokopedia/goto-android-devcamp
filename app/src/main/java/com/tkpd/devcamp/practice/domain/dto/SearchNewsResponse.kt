package com.tkpd.devcamp.practice.domain.dto

import com.google.gson.annotations.SerializedName
import com.tkpd.devcamp.practice.domain.model.NewsArticle

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
data class SearchNewsResponse(
    @SerializedName("articles")
    val articles: List<NewsArticle>
)