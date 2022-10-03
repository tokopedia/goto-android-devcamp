package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.api.ProductApi
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.model.Product

/**
 * Created by kenny.hadisaputra on 26/09/22
 */
class ProductMVVMViewModel(
    private val api: ProductApi
) : ViewModel() {

    //TODO(1,2) - Create product MutableLiveData
    //TODO(1,3) - Expose product MutableLiveData as LiveData
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    fun getProduct(productId: String) {
        //TODO(1,4) - Set value from API to product MutableLiveData
        _product.value = api.getProductById(productId)
    }
}