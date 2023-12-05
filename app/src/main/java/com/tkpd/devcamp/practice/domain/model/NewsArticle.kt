package com.tkpd.devcamp.practice.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
data class NewsArticle(
    @SerializedName("author")
    val author: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("urlToImage")
    val thumbnail: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("source")
    val source: Source = Source()
) {

    data class Source(
        @SerializedName("id")
        val id: String = "",

        @SerializedName("name")
        val name: String = "",
    )
}