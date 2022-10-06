package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.BannerAdapter
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

class BannerViewHolder(
    itemView: View,
    private val listener: Listener
) : HomeViewHolder(itemView) {

    private var bannerAdapter: BannerAdapter? = null
    private val rvBanner = itemView.findViewById<RecyclerView>(R.id.rv_banner)

    fun bind(item: BannerUiModel) {
        bannerAdapter = BannerAdapter(item.images, listener)
        rvBanner.adapter = bannerAdapter
    }

    interface Listener {
        fun onItemClicked(image: String, position: Int)
    }
}