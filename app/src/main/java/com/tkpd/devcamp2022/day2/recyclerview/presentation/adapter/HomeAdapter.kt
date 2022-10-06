package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.*
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.*

class HomeAdapter(
    private val bannerClickListener: BannerViewHolder.Listener,
    private val productClickListener: ProductViewHolder.Listener,
    private val squareBannerClickListener: SquareBannerViewHolder.Listener
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_banner -> BannerViewHolder(itemView, bannerClickListener)
            R.layout.item_title -> TitleViewHolder(itemView)
            R.layout.item_square_banner -> SquareBannerViewHolder(itemView, squareBannerClickListener)
            R.layout.item_product -> ProductViewHolder.Item(itemView, productClickListener)
            R.layout.item_product_shimmering -> ProductViewHolder.Placeholder(itemView)
            else -> throw IllegalAccessException("Unsupported viewType")
        }
    }

    private val itemList = mutableListOf<HomeUiModel>()

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> holder.bind(itemList[position] as BannerUiModel)
            is TitleViewHolder -> holder.bind(itemList[position] as TitleUiModel)
            is SquareBannerViewHolder -> holder.bind(itemList[position] as SquareBannerUiModel)
            is ProductViewHolder.Item -> holder.bind(itemList[position] as ProductUiModel.Item)
            else -> { }
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is BannerUiModel -> R.layout.item_banner
            is TitleUiModel -> R.layout.item_title
            is SquareBannerUiModel -> R.layout.item_square_banner
            is ProductUiModel.Item -> R.layout.item_product
            is ProductUiModel.Placeholder -> R.layout.item_product_shimmering
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