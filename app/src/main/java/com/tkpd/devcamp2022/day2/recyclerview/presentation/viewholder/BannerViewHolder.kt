package com.tkpd.devcamp2022.day2.recyclerview.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.BannerAdapter
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel

class BannerViewHolder(itemView: View) : HomeViewHolder(itemView) {

    private val recyclerView = itemView.findViewById<RecyclerView>(R.id.rv_banner)

    fun bind(bannerUiModel: BannerUiModel) {
        val bannerAdapter = BannerAdapter(bannerUiModel.images)
        recyclerView.adapter = bannerAdapter
    }
}