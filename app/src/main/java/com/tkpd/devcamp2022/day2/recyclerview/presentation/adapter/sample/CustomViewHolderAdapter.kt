package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.sample

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel


class CustomViewHolderAdapter(
    context: Context,
    resourceId: Int
) : ArrayAdapter<ProductUiModel.Item>(context, resourceId) {

    private class ViewHolder {
        var ivProduct: ImageView? = null
        var tvName: TextView? = null
        var tvPrice: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val dataModel = getItem(position) ?: throw IllegalStateException("data can't be null")

        var mConvertView = convertView
        val viewHolder: ViewHolder? // view lookup cache stored in tag

        if (mConvertView == null) {
            viewHolder = ViewHolder()

            val inflater = LayoutInflater.from(context)
            mConvertView = inflater.inflate(R.layout.item_product, parent, false)
            viewHolder.ivProduct = mConvertView.findViewById(R.id.iv_product)
            viewHolder.tvName = mConvertView.findViewById(R.id.tv_product_name)
            viewHolder.tvPrice = mConvertView.findViewById(R.id.tv_product_price)

            mConvertView.tag = viewHolder
        } else {
            viewHolder = mConvertView.tag as ViewHolder
            mConvertView = convertView
        }

        viewHolder.ivProduct?.setImageURI(Uri.parse(dataModel.image))
        viewHolder.tvName?.text = dataModel.name
        viewHolder.tvPrice?.text = dataModel.price

        return mConvertView ?: throw IllegalStateException("view can't be null")
    }
}