package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.sample

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel


class CustomRecyclerAdapter(
    private val items: List<ProductUiModel.Item>
) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
        val tvName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = items[position]

        holder.ivProduct.setImageURI(Uri.parse(dataModel.image))
        holder.tvName.text = dataModel.name
        holder.tvPrice.text = dataModel.price
    }

    override fun getItemCount(): Int {
        return items.size
    }
}