package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

class ProductViewHolder {

    class Placeholder constructor(
        itemView: View
    ) : HomeViewHolder(itemView)

    class Item constructor(
        itemView: View,
        private val listener: Listener
    ) : HomeViewHolder(itemView) {

        private val ivProduct = itemView.findViewById<ImageView>(R.id.iv_product)
        private val tvName = itemView.findViewById<TextView>(R.id.tv_product_name)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tv_product_price)
        private val tvLocation = itemView.findViewById<TextView>(R.id.tv_product_location)
        private val btnWishlist = itemView.findViewById<ImageButton>(R.id.btn_wishlist)
        private val btnAddToCart = itemView.findViewById<ImageButton>(R.id.btn_add_to_cart)

        fun bind(item: ProductUiModel.Item) {
            Glide.with(itemView.context)
                .load(item.image)
                .into(ivProduct)

            tvName.text = item.name
            tvPrice.text = item.price
            tvLocation.text = item.location

            itemView.setOnClickListener {
                listener.onItemClicked(item, adapterPosition)
            }

            btnWishlist.setOnClickListener {
                listener.onWishlistButtonClicked(item, adapterPosition)
            }
            btnAddToCart.setOnClickListener {
                listener.onAddToCartButtonClicked(item, adapterPosition)
            }
        }
    }

    interface Listener {
        fun onItemClicked(product: ProductUiModel, position: Int)
        fun onWishlistButtonClicked(product: ProductUiModel, position: Int)
        fun onAddToCartButtonClicked(product: ProductUiModel, position: Int)
    }
}