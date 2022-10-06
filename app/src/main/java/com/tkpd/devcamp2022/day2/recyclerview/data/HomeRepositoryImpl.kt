package com.tkpd.devcamp2022.day2.recyclerview.data

import com.tkpd.devcamp2022.day2.recyclerview.data.source.HomeDataSource
import com.tkpd.devcamp2022.day2.recyclerview.domain.HomeRepository
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.BannerUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.HomeUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.TitleUiModel

class HomeRepositoryImpl(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override fun getListOfImage(): List<String> {
        return dataSource.getBanner().images.shuffled()
    }

    override fun getBannerAndProducts(): List<HomeUiModel> {
        return mutableListOf<HomeUiModel>().apply {
            add(BannerUiModel(getListOfImage()))
            addAll(getProducts((0 until 10).random()))
        }
    }

    override fun getInitialHomeData(): List<HomeUiModel> {
        return mutableListOf<HomeUiModel>().apply {
            add(BannerUiModel(getListOfImage()))
            add(TitleUiModel("Rekomendasi Untukmu"))
            addAll(getProducts((0 until 10).random()))
        }
    }

    override fun getProducts(page: Int): List<ProductUiModel> {
        val products = dataSource.getProducts()
        return if (page % 2 == 0) products.take(5)
        else products.takeLast(5)
    }
}