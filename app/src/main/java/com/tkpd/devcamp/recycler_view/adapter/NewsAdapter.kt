package com.tkpd.devcamp.recycler_view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp.customview_bottomsheet.customview.NewsCard
import com.tkpd.devcamp.recycler_view.utils.customRecyclerViewDimension
import com.tkpd.devcamp.recycler_view.adapter.viewholder.NewsViewHolder
import com.tkpd.devcamp.recycler_view.model.Article

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _newsList = mutableListOf<Article>()
    private val newsList: List<Article>
        get() = _newsList

    fun insertData(data: List<Article>) {
        _newsList.clear()
        _newsList.addAll(data)
        notifyItemRangeInserted(0, newsList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            NewsCard(parent.context).apply {
                layoutParams = customRecyclerViewDimension
            }
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(newsList[position])
    }

}