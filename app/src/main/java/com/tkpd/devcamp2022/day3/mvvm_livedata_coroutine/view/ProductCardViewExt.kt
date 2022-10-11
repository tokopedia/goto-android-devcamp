package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.view

import coil.load
import com.tkpd.devcamp2022.databinding.ProductCardMvvmBinding
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.model.Product

/**
 * Created by kenny.hadisaputra on 27/09/22
 */
fun ProductCardMvvmBinding.setProduct(product: Product) {
    imgProduct.load(product.imageUrl)
    tvName.text = product.name
    tvPrice.text = product.price
    tvFreeShipping.text = if (product.freeShipping) "BEBAS ONGKIR" else ""

    if (product.stock != null) tvStock.text = "Stock: ${product.stock}"
}