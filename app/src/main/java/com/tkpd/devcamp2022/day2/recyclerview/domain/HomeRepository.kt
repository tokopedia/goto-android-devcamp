package com.tkpd.devcamp2022.day2.recyclerview.domain

import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.HomeUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

interface HomeRepository {

    fun getListOfImage(): List<String>

    fun getBannerAndTitles(): List<HomeUiModel>

    fun getInitialHomeData(): List<HomeUiModel>

    fun getProducts(page: Int): List<ProductUiModel>
}