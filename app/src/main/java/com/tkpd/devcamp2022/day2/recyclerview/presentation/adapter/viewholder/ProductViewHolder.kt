package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

class ProductViewHolder(
    itemView: View,
    private val listener: Listener
) : HomeViewHolder(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.iv_product)
    private val tvName = itemView.findViewById<TextView>(R.id.tv_product_name)
    private val tvPrice = itemView.findViewById<TextView>(R.id.tv_product_price)
    private val tvLocation = itemView.findViewById<TextView>(R.id.tv_product_location)

    private val btnWishlist = itemView.findViewById<ImageButton>(R.id.btn_wishlist)

    fun bind(productUiModel: ProductUiModel) {
        Glide.with(itemView.context)
            .load(productUiModel.image)
            .into(imageView)

        tvName.text = productUiModel.name
        tvPrice.text = productUiModel.price
        tvLocation.text = productUiModel.location

        btnWishlist.setOnClickListener {
            listener.onWishlistButtonClicked(productUiModel, adapterPosition)
        }
    }

    interface Listener {
        fun onWishlistButtonClicked(productUiModel: ProductUiModel, position: Int)
    }
}