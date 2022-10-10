package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R

class BannerAdapter(
    private val bannerList: List<String>
) : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.iv_banner)

        fun bind(image: String) {
            Glide.with(itemView.context)
                .load(image)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_banner_image, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = bannerList[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }
}