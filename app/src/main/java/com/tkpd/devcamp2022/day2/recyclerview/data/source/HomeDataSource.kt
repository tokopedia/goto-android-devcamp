package com.tkpd.devcamp2022.day2.recyclerview.data.source

import com.tkpd.devcamp2022.day2.recyclerview.data.mapper.HomeJsonMapper
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel
import com.tkpd.devcamp2022.day2.recyclerview.util.toJsonArray

class HomeDataSource(
    private val jsonMapper: HomeJsonMapper
) {

    fun getBanner(): BannerUiModel {
        val jsonArray = homeBannerData.toJsonArray()
        return jsonMapper.mapToBanner(jsonArray)
    }

    fun getProducts(): List<ProductUiModel> {
        val jsonArray = homeProductData.toJsonArray()
        return jsonMapper.mapToProduct(jsonArray)
    }

    private val homeBannerData = """
        [{
            "image": "https://images.tokopedia.net/img/cache/564/ymnyfH/2022/9/21/2b4c5696-c2a1-45b3-8298-a79709422b76.jpg"
        }, {
            "image": "https://images.tokopedia.net/img/cache/564/ymnyfH/2022/9/1/f398118b-20c7-421e-acf3-a6d6fd66eb7d.png"
        }, {
            "image": "https://images.tokopedia.net/img/cache/564/ymnyfH/2022/9/1/092af14b-d294-472f-b5a2-f8a91385422c.jpg"
        }]
    """.trimIndent()

    private val homeProductData = """
        [{
            "product_name": "Pot & Tanaman Kaktus Mini Cactus Leggie",
            "product_image": "https://images.tokopedia.net/img/cache/700/VqbcmM/2021/10/24/89d956f5-485a-473e-8514-5c9de7568d8b.png",
            "product_price": "Rp49.000",
            "product_location": "Kota Magelang"
        }, {
            "product_name": "Pot & Kaktus Mini Cloky Cactus - ToToRo",
            "product_image": "https://images.tokopedia.net/img/cache/900/VqbcmM/2022/3/20/43d92d02-9e9b-4522-88ba-d7c39d1f8990.png",
            "product_price": "Rp77.000",
            "product_location": "Kota Magelang"
        }, {
            "product_name": "Pot Kaktus Mini Kaki Daisy",
            "product_image": "https://images.tokopedia.net/img/cache/900/product-1/2019/2/13/395919808/395919808_5aefe5db-2013-4101-85cf-bfccf496eddf_717_717.jpg",
            "product_price": "Rp50.000",
            "product_location": "Kota Magelang"
        }, {
            "product_name": "Pot Kaktus Mini Concrete Panda",
            "product_image": "https://images.tokopedia.net/img/cache/900/product-1/2019/2/9/258134395/258134395_48096b76-c128-4313-8c78-ebfc0c6d300b_481_481.jpg",
            "product_price": "Rp50.000",
            "product_location": "Kota Magelang"
        }, {
            "product_name": "Pot & Kaktus Mini Leggie Cloky - Greedo",
            "product_image": "https://images.tokopedia.net/img/cache/700/VqbcmM/2021/10/24/89d956f5-485a-473e-8514-5c9de7568d8b.png",
            "product_price": "Rp67.000",
            "product_location": "Kota Magelang"
        }, {
            "product_name": "Pot Kaktus Mini Kaki Peachy",
            "product_image": "https://images.tokopedia.net/img/cache/900/product-1/2019/4/13/1490895/1490895_8b33d36e-3f71-4663-839e-6457439f9277_671_671.jpg",
            "product_price": "Rp50.000",
            "product_location": "Kota Magelang"
        }, {
            "product_name": "Pot Kaktus Mini Kaki Little Meaow",
            "product_image": "https://images.tokopedia.net/img/cache/900/product-1/2019/6/8/1490895/1490895_ef05f9de-0db7-481b-b24b-6b18bdc5285e_943_943.jpg",
            "product_price": "Rp50.000",
            "product_location": "Kota Magelang"
        }, {
            "product_name": "Pot Kaktus Mini Kaki Little Pink Cat",
            "product_image": "https://images.tokopedia.net/img/cache/900/product-1/2019/6/17/485254773/485254773_1f8396cb-0085-414c-82a6-ac0f37341ff2_565_565.jpg",
            "product_price": "Rp50.000",
            "product_location": "Kota Magelang"
        }, {
            "product_name": "Pot Kaktus Mini Kaki Fun",
            "product_image": "https://images.tokopedia.net/img/cache/900/product-1/2019/9/10/1490895/1490895_ce376694-7bdb-49d2-93cf-31b0faa0b9f1_1152_1152.jpg",
            "product_price": "Rp50.000",
            "product_location": "Kota Magelang"
        }]
    """.trimIndent()
}