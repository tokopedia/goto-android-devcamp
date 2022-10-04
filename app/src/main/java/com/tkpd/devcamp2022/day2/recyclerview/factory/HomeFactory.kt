package com.tkpd.devcamp2022.day2.recyclerview.factory

import com.tkpd.devcamp2022.day2.recyclerview.data.HomeRepositoryImpl
import com.tkpd.devcamp2022.day2.recyclerview.data.mapper.HomeJsonMapper
import com.tkpd.devcamp2022.day2.recyclerview.data.source.HomeDataSource
import com.tkpd.devcamp2022.day2.recyclerview.domain.HomeRepository

object HomeFactory {

    private fun provideJsonMapper(): HomeJsonMapper = HomeJsonMapper()

    private fun provideDataSource(jsonMapper: HomeJsonMapper): HomeDataSource = HomeDataSource(jsonMapper)

    private fun provideRepository(dataSource: HomeDataSource): HomeRepository = HomeRepositoryImpl(dataSource)

    fun getRepository(): HomeRepository {
        return provideRepository(
            dataSource = provideDataSource(
                jsonMapper = provideJsonMapper()
            )
        )
    }
}