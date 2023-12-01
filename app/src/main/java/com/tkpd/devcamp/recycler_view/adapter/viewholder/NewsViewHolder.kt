package com.tkpd.devcamp.recycler_view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tkpd.devcamp.customview_bottomsheet.customview.NewsCard
import com.tkpd.devcamp.recycler_view.model.Article

class NewsViewHolder(
    private val newsCardView: NewsCard,
) : ViewHolder(newsCardView.rootView) {

    fun bind(data: Article) {
        newsCardView.setTitle(data.title)
        newsCardView.setAuthor(data.author)
        newsCardView.setDescription(data.description)
        newsCardView.setImageUrl(data.urlToImage)
        newsCardView.setOnClick(
            newsTitle = data.title,
            newsSource = data.source.name,
            newsAuthor = data.author,
            newsUrl = data.url,
            newsImageUrl = data.urlToImage,
        )
    }

}