package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.BannerAdapter
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel

class BannerViewHolder(itemView: View) : HomeViewHolder(itemView) {

    private var bannerAdapter: BannerAdapter? = null
    private val rvBanner = itemView.findViewById<RecyclerView>(R.id.rv_banner)

    fun bind(item: BannerUiModel) {
        bannerAdapter = BannerAdapter(item.images)
        rvBanner.adapter = bannerAdapter
    }
}