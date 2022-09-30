package com.tkpd.devcamp2022.day2.recyclerview.data

import com.tkpd.devcamp2022.day2.recyclerview.data.source.HomeDataSource
import com.tkpd.devcamp2022.day2.recyclerview.domain.HomeRepository
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.HomeUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

class HomeRepositoryImpl(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override fun getFirstInitialData(): List<HomeUiModel> {
        val initialData = dataSource.getSchema().toMutableList()
        initialData[0] = getBanners() // replace with real banners
        initialData.removeAt(2) // remove empty product
        initialData.addAll(getProducts(5, 0)) // add real products
        return initialData
    }

    override fun getBanners(): BannerUiModel {
        return dataSource.getBanner()
    }

    override fun getProducts(limit: Int, page: Int): List<ProductUiModel> {
        val products = dataSource.getProducts()
        return if (page % 2 == 0) products.take(limit)
        else products.takeLast(limit)
    }
}