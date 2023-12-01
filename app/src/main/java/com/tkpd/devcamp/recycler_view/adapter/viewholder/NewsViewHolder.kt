package com.tkpd.devcamp.recycler_view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tkpd.devcamp.customview_bottomsheet.customview.NewsCard
import com.tkpd.devcamp.recycler_view.model.Article

class NewsViewHolder(
    private val newsCardView: NewsCard,
) : ViewHolder(newsCardView.rootView) {

    fun bind(data: Article) {
        TODO("bind your data into view-holder item as a views")
    }

}