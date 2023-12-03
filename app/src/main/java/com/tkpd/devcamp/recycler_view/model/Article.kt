package com.tkpd.devcamp.recycler_view.model

import com.tkpd.devcamp.connect_to_internet.data.model.NewsApiTopHeadlinesResponse.Article

data class Article(
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val source: Source = Source(),
    val title: String = "",
    val url: String = "",
    val urlToImage: String = "",
) {
    constructor(article: Article) :
            this(
                author = article.author.orEmpty(),
                content = article.content.orEmpty(),
                description = article.description.orEmpty(),
                publishedAt = article.publishedAt.orEmpty(),
                source = Source(
                    id = article.source?.id.orEmpty(),
                    name = article.source?.name.orEmpty()
                ),
                title = article.title.orEmpty(),
                url = article.url.orEmpty(),
                urlToImage = article.urlToImage.orEmpty()
            )

}