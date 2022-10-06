package com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel

sealed class ProductUiModel : HomeUiModel() {

    object Placeholder : ProductUiModel()

    data class Item(
        val image: String,
        val name: String,
        val price: String,
        val location: String,
    ): ProductUiModel() {
        companion object {
            val empty = Item(
                image = "",
                name = "",
                price = "",
                location = ""
            )
        }
    }
}
