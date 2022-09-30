package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.*
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.*

class HomeAdapter(
    private val productClickListener: ProductViewHolder.Listener
) : RecyclerView.Adapter<HomeViewHolder>() {

    private val itemList = mutableListOf<HomeUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_banner_parent -> BannerViewHolder(itemView)
            R.layout.item_title -> TitleViewHolder(itemView)
            R.layout.item_product -> ProductViewHolder(itemView, productClickListener)
            R.layout.item_product_shimmering -> ProductShimmeringViewHolder(itemView)
            else -> throw IllegalAccessException("No supported viewType")
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> holder.bind(itemList[position] as BannerUiModel)
            is TitleViewHolder -> holder.bind(itemList[position] as TitleUiModel)
            is ProductViewHolder -> holder.bind(itemList[position] as ProductUiModel)
            is ProductShimmeringViewHolder -> holder.bind(itemList[position] as ProductShimmeringUiModel)
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is BannerUiModel -> R.layout.item_banner_parent
            is TitleUiModel -> R.layout.item_title
            is ProductUiModel -> R.layout.item_product
            is ProductShimmeringUiModel -> R.layout.item_product_shimmering
            else -> super.getItemViewType(position)
        }
    }

    fun setItems(itemList: List<HomeUiModel>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    fun insertItems(itemList: List<HomeUiModel>) {
        val startPosition = itemCount
        this.itemList.addAll(itemList)
        notifyItemRangeInserted(startPosition, itemCount)
    }

    fun insertItemAtLast(item: HomeUiModel) {
        this.itemList.add(itemList.size, item)
        notifyItemInserted(itemCount)
    }

    fun removeItemAtLast() {
        this.itemList.removeAt(itemList.lastIndex)
        notifyItemRemoved(itemCount)
    }
}