package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data.News

class NewsListAdapter() : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private var listNews = listOf<News>()

    @SuppressLint("NotifyDataSetChanged")
    fun setNews(allNews: List<News>) {
        this.listNews = allNews
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNews[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: News) {
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val ivNews = itemView.findViewById<ImageView>(R.id.ivNews)
            itemView.apply {
                tvTitle.text = news.title
                Glide.with(context)
                    .load(news.urlImage)
                    .error(R.drawable.ic_broken_image)
                    .apply(RequestOptions().dontTransform().placeholder(R.drawable.ic_loading))
                    .into(ivNews)

            }
        }
    }

}