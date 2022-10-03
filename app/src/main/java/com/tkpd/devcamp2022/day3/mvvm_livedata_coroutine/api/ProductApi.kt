package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.api

import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.model.Product

/**
 * Created by kenny.hadisaputra on 26/09/22
 */
interface ProductApi {

    fun getProductById(productId: String): Product

    //TODO(2,1) - change this function to suspending function
    suspend fun getProductSlower(productId: String): Product

    suspend fun getProductStock(productId: String): Int
}