package com.tkpd.devcamp.connect_to_internet.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp.connect_to_internet.presentation.adapter.viewholder.NewsViewHolder
import com.tkpd.devcamp.customview_bottomsheet.customview.NewsCard
import com.tkpd.devcamp.recycler_view.model.Article
import com.tkpd.devcamp.recycler_view.utils.customRecyclerViewDimension

class NewsAdapter : ListAdapter<Article, RecyclerView.ViewHolder>(ItemDiffer()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            NewsCard(parent.context).apply {
                layoutParams = customRecyclerViewDimension
            }
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(getItem(position))
    }

}

class ItemDiffer : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}