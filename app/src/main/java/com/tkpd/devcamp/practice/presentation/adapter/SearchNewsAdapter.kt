package com.tkpd.devcamp.practice.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp.customview_bottomsheet.customview.NewsCard
import com.tkpd.devcamp.practice.domain.model.NewsArticle
import com.tkpd.devcamp.practice.presentation.adapter.viewholder.SearchNewsViewHolder
import com.tkpd.devcamp.recycler_view.utils.customRecyclerViewDimension

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
class SearchNewsAdapter : ListAdapter<NewsArticle, RecyclerView.ViewHolder>(ItemDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Complete this function")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Complete this function")
    }
}

class ItemDiffer : DiffUtil.ItemCallback<NewsArticle>() {

    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        TODO("Complete this function")
    }

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        TODO("Complete this function")
    }
}