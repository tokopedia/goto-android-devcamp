package com.tkpd.devcamp2022.day2.recyclerview.domain

import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.HomeUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.SquareBannerUiModel

interface HomeRepository {

    fun getFirstInitialData(): List<HomeUiModel>

    fun getBanners(): BannerUiModel

    fun getSquareBanners(): SquareBannerUiModel

    fun getProducts(limit: Int, page: Int): List<ProductUiModel>
}