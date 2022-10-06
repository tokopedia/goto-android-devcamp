package com.tkpd.devcamp2022.day2.recyclerview.data

import com.tkpd.devcamp2022.day2.recyclerview.data.source.HomeDataSource
import com.tkpd.devcamp2022.day2.recyclerview.domain.HomeRepository
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.HomeUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

class HomeRepositoryImpl(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override fun getListOfImage(): List<String> {
        return dataSource.getBanner().images.shuffled()
    }

    override fun getBannerAndTitles(): List<HomeUiModel> {
        val schema = dataSource.getSchema().toMutableList()
        schema[0] = BannerUiModel(getListOfImage()) // replace with real banners
        schema.removeAt(2) // remove empty product
        return schema
    }

    override fun getInitialHomeData(): List<HomeUiModel> {
        val schema = getBannerAndTitles().toMutableList()
        schema.addAll(getProducts((0 until 10).random())) // add real products
        return schema
    }

    override fun getProducts(page: Int): List<ProductUiModel> {
        val products = dataSource.getProducts()
        return if (page % 2 == 0) products.take(5)
        else products.takeLast(5)
    }
}