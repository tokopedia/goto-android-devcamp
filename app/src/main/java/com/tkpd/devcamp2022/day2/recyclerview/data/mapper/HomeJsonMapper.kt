package com.tkpd.devcamp2022.day2.recyclerview.data.mapper

import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.HomeUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.TitleUiModel
import org.json.JSONArray
import org.json.JSONObject

class HomeJsonMapper {

    fun map(jsonArray: JSONArray): List<HomeUiModel?> {
        return List(jsonArray.length()) { index ->
            val jsonObj = jsonArray.optJSONObject(index)
            when {
                jsonObj.optString("type") == "banner" -> {
                    BannerUiModel(emptyList())
                }
                jsonObj.optString("type") == "title" -> {
                    mapToTitle(jsonObj.getJSONObject("content"))
                }
                jsonObj.optString("type") == "product" -> {
                    ProductUiModel(
                        "",
                        "",
                        "",
                        ""
                    )
                }
                else -> {
                    null
                }
            }
        }
    }

    fun mapToBanner(jsonArray: JSONArray): BannerUiModel {
        return BannerUiModel(
            List(jsonArray.length()) { index ->
                jsonArray.optJSONObject(index).optString("image")
            }
        )
    }

    fun mapToProduct(jsonArray: JSONArray): List<ProductUiModel> {
        return List(jsonArray.length()) { index ->
            val jsonObj = jsonArray.optJSONObject(index)
            ProductUiModel(
                name = jsonObj.optString("product_name"),
                image = jsonObj.optString("product_image"),
                price = jsonObj.optString("product_price"),
                location = jsonObj.optString("product_location"),
            )
        }
    }

    private fun mapToTitle(jsonObject: JSONObject): TitleUiModel {
        return TitleUiModel(
            jsonObject.optString("label")
        )
    }
}