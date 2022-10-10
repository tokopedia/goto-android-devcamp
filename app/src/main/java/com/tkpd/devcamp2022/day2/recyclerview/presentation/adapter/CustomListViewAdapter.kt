package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

class CustomListViewAdapter(private val itemList: List<ProductUiModel>) : BaseAdapter() {

    class ViewHolder {
        var ivProduct: ImageView? = null
        var tvName: TextView? = null
        var tvPrice: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val dataModel = getItem(position)

        var mConvertView = convertView
        val viewHolder: ViewHolder?

        if (mConvertView == null) {
            viewHolder = ViewHolder()
            mConvertView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_product, parent, false)

            viewHolder.ivProduct = mConvertView.findViewById(R.id.iv_product)
            viewHolder.tvName = mConvertView.findViewById(R.id.tv_product_name)
            viewHolder.tvPrice = mConvertView.findViewById(R.id.tv_product_price)

            mConvertView.tag = viewHolder
        } else {
            viewHolder = mConvertView.tag as ViewHolder
        }

        val ivProduct: ImageView = viewHolder.ivProduct ?: throw IllegalStateException("imageview can't be null")
        Glide.with(parent.context)
            .load(dataModel.image)
            .into(ivProduct)

        viewHolder.tvName?.text = dataModel.name
        viewHolder.tvPrice?.text = dataModel.price

        return mConvertView ?: throw IllegalStateException("view can't be null")
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(position: Int): ProductUiModel {
        return itemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}