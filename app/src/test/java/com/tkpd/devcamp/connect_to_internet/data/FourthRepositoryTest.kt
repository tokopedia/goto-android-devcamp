package com.tkpd.devcamp.connect_to_internet.data

import DataHelper
import com.google.gson.stream.MalformedJsonException
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType
import com.tkpd.devcamp.connect_to_internet.network.ApiResult
import com.tkpd.devcamp.connect_to_internet.network.NewsApiRemoteDataSource
import com.tkpd.devcamp.connect_to_internet.network.getApiError
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class FourthRepositoryTest {

    private lateinit var repository: FourthRepository

    private val mockRemote: NewsApiRemoteDataSource = mockk(relaxUnitFun = true)

    @Before
    fun setup() {
        repository = FourthRepository(remoteDataSource = mockRemote)
    }

    @Test
    fun `return api success`() = runBlocking {
        //given
        val expected = DataHelper.createFakeResponse()
        coEvery { mockRemote.topHeadlineNews(any(), any()) } returns expected

        //when
        val actual = repository.getTopHeadlines()

        //then
        coVerify { mockRemote.topHeadlineNews(any(), any()) }
        assert(expected.articles == (actual as ApiResult.Success).data.articles)
    }

    @Test
    fun `return api error server`() = runBlocking {
        //given
        val expected = MalformedJsonException("this is server error")
        coEvery { mockRemote.topHeadlineNews(any(), any()) } throws expected

        //when
        val actual = repository.getTopHeadlines()

        //then
        coVerify { mockRemote.topHeadlineNews(any(), any()) }
        val mapped = getApiError(expected)
        assert(actual is ApiResult.Error)
        assert((actual as ApiResult.Error).apiErrorType == mapped)
        assert(mapped is ApiErrorType.SERVER)
    }

    @Test
    fun `return api error network`() = runBlocking {
        //given
        val expected = IOException()
        coEvery { mockRemote.topHeadlineNews(any(), any()) } throws expected

        //when
        val actual = repository.getTopHeadlines()

        //then
        coVerify { mockRemote.topHeadlineNews(any(), any()) }
        val mapped = getApiError(expected)
        assert((actual as ApiResult.Error).apiErrorType == mapped)
        assert(mapped is ApiErrorType.NETWORK)
    }

    @Test
    fun `return api error general`() = runBlocking {
        //given
        val expected = Exception()
        coEvery { mockRemote.topHeadlineNews(any(), any()) } throws expected

        //when
        val actual = repository.getTopHeadlines()

        //then
        coVerify { mockRemote.topHeadlineNews(any(), any()) }
        val mapped = getApiError(expected)
        assert((actual as ApiResult.Error).apiErrorType == mapped)
        assert(mapped is ApiErrorType.UNKNOWN)
    }
}