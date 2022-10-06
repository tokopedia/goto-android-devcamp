package com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel

data class SquareBannerUiModel(
    val images: List<String>
): HomeUiModel() {
    companion object {
        val empty = SquareBannerUiModel(emptyList())
    }
}
