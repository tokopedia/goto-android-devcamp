package com.tkpd.devcamp2022.day2.recyclerview.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.HomeUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.TitleUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.viewholder.BannerViewHolder
import com.tkpd.devcamp2022.day2.recyclerview.presentation.viewholder.HomeViewHolder
import com.tkpd.devcamp2022.day2.recyclerview.presentation.viewholder.ProductViewHolder
import com.tkpd.devcamp2022.day2.recyclerview.presentation.viewholder.TitleViewHolder

class HomeAdapter(
    private val productListener: ProductViewHolder.Listener
) : RecyclerView.Adapter<HomeViewHolder>() {

    private val itemList = mutableListOf<HomeUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_banner -> BannerViewHolder(itemView)
            R.layout.item_product -> ProductViewHolder(itemView, productListener)
            R.layout.item_title -> TitleViewHolder(itemView)
            else -> throw IllegalStateException("unsupported")
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> holder.bind(itemList[position] as BannerUiModel)
            is ProductViewHolder -> holder.bind(itemList[position] as ProductUiModel)
            is TitleViewHolder -> holder.bind(itemList[position] as TitleUiModel)
            else -> { }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position]) {
            is BannerUiModel -> R.layout.item_banner
            is ProductUiModel -> R.layout.item_product
            is TitleUiModel -> R.layout.item_title
            else -> super.getItemViewType(position)
        }
    }

    fun setItems(homeData: List<HomeUiModel>) {
        this.itemList.clear()
        this.itemList.addAll(homeData)
        notifyDataSetChanged()
    }

    fun insertItems(homeData: List<HomeUiModel>) {
        val startPosition = itemCount
        this.itemList.addAll(homeData)
        notifyItemRangeInserted(startPosition, itemCount)
    }
}