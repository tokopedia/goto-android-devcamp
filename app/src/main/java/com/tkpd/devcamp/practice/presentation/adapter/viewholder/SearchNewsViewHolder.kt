package com.tkpd.devcamp.practice.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp.customview_bottomsheet.customview.NewsCard
import com.tkpd.devcamp.practice.domain.model.NewsArticle

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
class SearchNewsViewHolder(
    private val newsCardView: NewsCard
) : RecyclerView.ViewHolder(newsCardView.rootView) {

    fun bind(data: NewsArticle) {
        newsCardView.setTitle(data.title.orEmpty())
        newsCardView.setAuthor(data.author.orEmpty())
        newsCardView.setDescription(data.description.orEmpty())
        newsCardView.setImageUrl(data.thumbnail.orEmpty())
        newsCardView.setOnClick(
            newsTitle = data.title,
            newsSource = data.source.name,
            newsAuthor = data.author,
            newsUrl = data.url,
            newsImageUrl = data.thumbnail,
        )
    }
}