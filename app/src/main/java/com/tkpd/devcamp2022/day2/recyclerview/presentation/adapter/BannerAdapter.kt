package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.BannerViewHolder

class BannerAdapter(
    private val itemList: List<String>,
    private val bannerListener: BannerViewHolder.Listener
) : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_banner_child, parent, false),
            listener = bannerListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View, private val listener: BannerViewHolder.Listener) : RecyclerView.ViewHolder(itemView) {

        private val ivBanner = itemView.findViewById<ImageView>(R.id.iv_banner)

        fun bind(image: String) {
            Glide.with(itemView.context)
                .load(image)
                .into(ivBanner)

            itemView.setOnClickListener {
                listener.onItemClicked(image, adapterPosition)
            }
        }
    }
}