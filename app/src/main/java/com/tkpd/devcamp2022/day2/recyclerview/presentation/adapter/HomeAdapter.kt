package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.HomeUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.TitleUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.BannerViewHolder
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.HomeViewHolder
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.ProductViewHolder
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.TitleViewHolder

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

    /**
     * Return the view type of the item at position for the purposes of view recycling.
     * The default implementation of this method returns 0, making the assumption of a single view type for the adapter. Unlike ListView adapters, types need not be contiguous. Consider using id resources to uniquely identify item view types.
     * Params: position – position to query
     * Returns: integer value identifying the type of the view needed to represent the item at position. Type codes need not be contiguous.
     */
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
        // as you can see in your android studio,
        // it says: it will always be more efficient to use more specific change events if you can. Rely on notifyDataSetChanged as a last resort.
        // however, since we are changing the whole data, we need to notify whole items in the adapter.
        notifyDataSetChanged()
    }

    fun insertItems(homeData: List<HomeUiModel>) {
        val startPosition = itemCount
        this.itemList.addAll(homeData)
        // we'll only notify the new items we inserted to this adapter
        notifyItemRangeInserted(startPosition, itemCount)
    }
}