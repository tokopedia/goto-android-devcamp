package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R

class BannerAdapter(
    private val itemList: List<String>
) : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner_child, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivBanner = itemView.findViewById<ImageView>(R.id.iv_banner)

        fun bind(image: String) {
            Glide.with(itemView.context)
                .load(image)
                .into(ivBanner)
        }
    }
}