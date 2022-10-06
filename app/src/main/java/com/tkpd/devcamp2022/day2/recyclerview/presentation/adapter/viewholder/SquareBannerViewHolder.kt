package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.SquareBannerUiModel

class SquareBannerViewHolder(
    itemView: View,
    private val listener: Listener
) : HomeViewHolder(itemView) {

    private val ivBannerLeft = itemView.findViewById<ImageView>(R.id.iv_square_banner_1)
    private val ivBannerRight = itemView.findViewById<ImageView>(R.id.iv_square_banner_2)

    fun bind(item: SquareBannerUiModel) {
        Glide.with(itemView.context)
            .load(item.images.first())
            .into(ivBannerLeft)

        Glide.with(itemView.context)
            .load(item.images[1])
            .into(ivBannerRight)

        ivBannerLeft.setOnClickListener {
            listener.onItemClicked(item.images.first(), 0)
        }

        ivBannerRight.setOnClickListener {
            listener.onItemClicked(item.images[1], 1)
        }
    }

    interface Listener {
        fun onItemClicked(image: String, position: Int)
    }
}