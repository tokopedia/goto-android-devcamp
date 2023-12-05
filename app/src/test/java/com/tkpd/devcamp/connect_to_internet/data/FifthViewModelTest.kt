package com.tkpd.devcamp.connect_to_internet.data

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tkpd.devcamp.viewmodel_livedata.FifthViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FifthViewModelTest {
    //Add MainCoroutineRule
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    //Add InstantTaskExecutorRule
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //Declare viewModel
    private lateinit var viewModel: FifthViewModel

    //TODO() mock Repository

    //TODO() mock Observer to observe Live Data

    @Before
    fun setup() {
        //TODO() init viewmodel here

        //TODO() setup Repository

        //TODO() add observer for Live Data here
    }

    @After
    fun finish() {
        //TODO() remove Live Data observer
    }

    @Test
    fun `test - when error from network occurs emit error state`() {
        //given
        //TODO() mock data here [expected data] and stub repository

        //when
        //TODO() call function {viewModel.getTopHeadline()}

        //then
        //TODO() assert [compare with actual result with expected]
    }

    @Test
    fun `test - when error from auth occurs emit error state`() {
        //given
        //TODO() mock data here [expected data]

        //when
        //TODO() call function {viewModel.getTopHeadline()}

        //then
        //TODO() assert [compare with actual result with expected]
    }

    @Test
    fun `test - when error from server occurs emit error state`() {
        //given
        //TODO() mock data here [expected data]

        //when
        //TODO() call function {viewModel.getTopHeadline()}

        //then
        //TODO() assert [compare with actual result with expected]
    }

    @Test
    fun `test - when error from client occurs emit error state`() {
        //given
        //TODO() mock data here [expected data]

        //when
        //TODO() call function {viewModel.getTopHeadline()}

        //then
        //TODO() assert [compare with actual result with expected]
    }

    @Test
    fun `test - when error from unknown occurs emit error state`() {
        //given
        //TODO() mock data here [expected data]

        //when
        //TODO() call function {viewModel.getTopHeadline()}

        //then
        //TODO() assert [compare with actual result with expected]
    }

    @Test
    fun `test - when success return success state`() {
        //given
        //TODO() mock data here [expected data]

        //when
        //TODO() call function {viewModel.getTopHeadline()}

        //then
        //TODO() assert [compare with actual result with expected]
    }

    @Test
    fun `test - when error occurs, observe data`() {
        //given
        //TODO() mock data here [expected data]

        //when
        //TODO() call function {viewModel.getTopHeadline()}

        //then
        //TODO() assert [compare with actual result with expected] - verifu value sequentially
    }

    @Test
    fun `test - when success occurs, observe data`() {
        //given
        //TODO() mock data here [expected data]

        //when
        //TODO() call function {viewModel.getTopHeadline()}

        //then
        //TODO() assert [compare with actual result with expected] - verifu value sequentially
    }
}