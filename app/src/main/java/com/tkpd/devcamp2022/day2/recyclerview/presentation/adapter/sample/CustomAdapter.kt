package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.sample

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel


class CustomAdapter(
    private val context: Context,
    private val itemList: List<ProductUiModel.Item>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val dataModel = getItem(position)

        val itemView = LayoutInflater
            .from(context)
            .inflate(R.layout.item_product, parent, false)
        val ivProduct = itemView.findViewById<ImageView>(R.id.iv_product)
        val tvName = itemView.findViewById<TextView>(R.id.tv_product_name)
        val tvPrice = itemView.findViewById<TextView>(R.id.tv_product_price)

        ivProduct.setImageURI(Uri.parse(dataModel.image))
        tvName.text = dataModel.name
        tvPrice.text = dataModel.price

        return itemView
    }

    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): ProductUiModel.Item = itemList[position]

    override fun getItemId(position: Int): Long = itemList[position].hashCode().toLong()
}