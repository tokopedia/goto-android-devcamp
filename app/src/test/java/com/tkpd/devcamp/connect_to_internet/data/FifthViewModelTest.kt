package com.tkpd.devcamp.connect_to_internet.data

import DataHelper
import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType
import com.tkpd.devcamp.connect_to_internet.network.ApiResult
import com.tkpd.devcamp.recycler_view.model.Article
import com.tkpd.devcamp.viewmodel_livedata.FifthViewModel
import com.tkpd.devcamp.viewmodel_livedata.state.ArticleScreenState
import getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifySequence
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FifthViewModelTest {
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository: FourthRepository = mockk()

    private lateinit var viewModel: FifthViewModel

    private val mockObserver: Observer<ArticleScreenState> = mockk(relaxUnitFun = true)

    @Before
    fun setup() {
        viewModel = FifthViewModel(mainCoroutineRule.dispatcher, mainCoroutineRule.coroutineContext)
        viewModel.setupRepository(repository)

        viewModel.state.observeForever(mockObserver)
    }

    @After
    fun finish() {
        viewModel.state.removeObserver(mockObserver)
    }

    /**
     * Test Case 1: initial load -> ArticleScreenState.Loading
     * - Api Error [AUTH, CLIENT (with errors), NETWORK, SERVER, UNKNOWN]
     * - Api Success: empty list, list with arrar
     */

    @Test
    fun `test - error network`() {
        //given
        val mockData = DataHelper.createFakeError(error = ApiErrorType.NETWORK)
        coEvery { repository.getTopHeadlines() } returns mockData

        //when
        viewModel.getTopHeadline()

        //then
        val result = viewModel.state.getOrAwaitValue()
        assert(result is ArticleScreenState.Error)
    }

    @Test
    fun `test - error auth`() {
        //given
        val mockData = DataHelper.createFakeError(error = ApiErrorType.AUTH)
        coEvery { repository.getTopHeadlines() } returns mockData

        //when
        viewModel.getTopHeadline()

        //then
        val result = viewModel.state.getOrAwaitValue()
        assert(result is ArticleScreenState.Error)
    }

    @Test
    fun `test - error server`() {
        //given
        val mockData = DataHelper.createFakeError(error = ApiErrorType.SERVER)
        coEvery { repository.getTopHeadlines() } returns mockData

        //when
        viewModel.getTopHeadline()

        //then
        val result = viewModel.state.getOrAwaitValue()
        assert(result is ArticleScreenState.Error)
    }

    @Test
    fun `test - error client`() {
        //given
        val mockData =
            DataHelper.createFakeError(error = ApiErrorType.CLIENT(errors = listOf(Error("client"))))
        coEvery { repository.getTopHeadlines() } returns mockData

        //when
        viewModel.getTopHeadline()

        //then
        val result = viewModel.state.getOrAwaitValue()
        assert(result is ArticleScreenState.Error)
    }

    @Test
    fun `test - error unknown`() {
        //given
        val mockData = DataHelper.createFakeError(error = ApiErrorType.UNKNOWN)
        coEvery { repository.getTopHeadlines() } returns mockData

        //when
        viewModel.getTopHeadline()

        //then
        val result = viewModel.state.getOrAwaitValue()
        assert(result is ArticleScreenState.Error)
    }

    @Test
    fun `test - success`() {
        //given
        val mockData = DataHelper.createFakeClass()
        coEvery { repository.getTopHeadlines() } returns mockData

        //when
        viewModel.getTopHeadline()

        //then
        val result = viewModel.state.getOrAwaitValue()
        assert(result is ArticleScreenState.Success)
        assertEquals(
            (result as ArticleScreenState.Success).list.size,
            (mockData as ApiResult.Success).data.articles.size
        )
    }

    @Test
    fun `test 1 - initial state loading with success return`() {
        //given
        val mockData = DataHelper.createFakeClass()
        coEvery { repository.getTopHeadlines() } returns mockData

        //when
        viewModel.getTopHeadline()

        //then
        verifySequence {
            mockObserver.onChanged(ArticleScreenState.Loading)
            mockObserver.onChanged(ArticleScreenState.Success(
                (mockData as ApiResult.Success).data.articles.map {
                    Article(it)
                }
            ))
        }
    }
}