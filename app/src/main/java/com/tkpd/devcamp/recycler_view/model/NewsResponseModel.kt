package com.tkpd.devcamp.recycler_view.model

data class NewsResponseModel(
    val articles: List<Article> = emptyList(),
    val status: String = "",
    val totalResults: Int = 0,
)