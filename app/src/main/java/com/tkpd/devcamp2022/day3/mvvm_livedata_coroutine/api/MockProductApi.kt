package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.api

import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * Created by kenny.hadisaputra on 26/09/22
 */
class MockProductApi : ProductApi {

    private val productMap = mutableMapOf<String, Product>()

    private val randomizer: Int
        get() = Random.nextInt(0, 59)

    override fun getProductById(productId: String): Product {
        return productMap[productId] ?: run {
            val product = createProduct(productId)
            productMap[productId] = product
            product
        }
    }

    //TODO(2,2) - call this function with in IO thread
    override suspend fun getProductSlower(productId: String): Product = withContext(Dispatchers.IO) {
        Thread.sleep(3000)
        return@withContext getProductById(productId)
    }

    override suspend fun getProductStock(productId: String): Int = withContext(Dispatchers.IO) {
        Thread.sleep(3000)
        return@withContext (productId.toIntOrNull() ?: 0) % 50
    }

    private fun createProduct(id: String): Product {
        val randomNumber = randomizer

        return Product(
            id = id,
            name = "Product $id",
            imageUrl = getProductImage(randomNumber),
            price = "Rp $randomNumber.000",
            freeShipping = randomNumber % 2 == 0,
        )
    }

    private fun getProductImage(number: Int): String {
        return when (number % 5) {
            0 -> "https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?cs=srgb&dl=pexels-math-90946.jpg&fm=jpg"
            1 -> "https://images.pexels.com/photos/335257/pexels-photo-335257.jpeg?cs=srgb&dl=pexels-eprism-studio-335257.jpg&fm=jpg"
            2 -> "https://images.pexels.com/photos/821651/pexels-photo-821651.jpeg?cs=srgb&dl=pexels-alex-andrews-821651.jpg&fm=jpg"
            3 -> "https://images.pexels.com/photos/258244/pexels-photo-258244.jpeg?cs=srgb&dl=pexels-pixabay-258244.jpg&fm=jpg"
            else -> "https://images.pexels.com/photos/1738641/pexels-photo-1738641.jpeg?cs=srgb&dl=pexels-luis-quintero-1738641.jpg&fm=jpg"
        }
    }
}