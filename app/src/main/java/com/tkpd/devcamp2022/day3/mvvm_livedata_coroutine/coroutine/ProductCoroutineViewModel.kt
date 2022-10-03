package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.api.ProductApi
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.model.Product
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by kenny.hadisaputra on 26/09/22
 */
class ProductCoroutineViewModel(
    private val api: ProductApi,
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() = _seconds

    private val timer = Timer()

    init {
        timer.scheduleAtFixedRate(object : TimerTask() {
            private var prevTimer = System.currentTimeMillis()

            override fun run() {
                viewModelScope.launch {
                    _seconds.value = TimeUnit.MILLISECONDS.toSeconds(
                        System.currentTimeMillis() - prevTimer
                    ).toInt()
                }
            }
        }, 0, 1000L)
    }

    fun getProduct(productId: String) {
        _product.value = api.getProductById(productId)
    }

    fun getProductSlower(productId: String) {
        //TODO(2,3) - Call the suspending function with a coroutine builder
        viewModelScope.launch {
            _product.value = api.getProductSlower(productId)
        }
    }

    fun getProductWithStock(productId: String) {
        viewModelScope.launch {
            //TODO(3,1) - make both these api calls async
            //TODO(3,2) - await both async functions and combine the result, then set it to livedata
            val product = async { api.getProductSlower(productId) }
            val stock = async { api.getProductStock(productId) }

            _product.value = product.await().copy(
                stock = stock.await(),
            )
        }
    }
}