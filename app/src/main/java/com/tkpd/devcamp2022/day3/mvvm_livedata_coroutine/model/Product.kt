package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.model

/**
 * Created by kenny.hadisaputra on 26/09/22
 */
data class Product(
    val id: String,
    val name: String,
    val imageUrl: String,
    val price: String,
    val freeShipping: Boolean,
    val stock: Int? = null,
)