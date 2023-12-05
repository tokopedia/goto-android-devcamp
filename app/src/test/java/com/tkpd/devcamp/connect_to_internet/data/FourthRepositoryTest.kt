package com.tkpd.devcamp.connect_to_internet.data

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FourthRepositoryTest {
    //Declare repository.
    private lateinit var repository: FourthRepository

    //TODO() mock Data Source.

    @Before
    fun setup() {
        //TODO() init Repository here
    }

    @Test
    fun `return api success`() = runBlocking {
        //given
        //TODO() mock data here [expected data], stub data remote source.

        //when
        //TODO() call function that we want to test [repository.getTopHeadlines()]

        //then
        //TODO() verify and assert is the actual result == expected?
    }

    @Test
    fun `return api error server`() = runBlocking {
        //given
        //TODO() mock data here [expected data], stub data remote source.

        //when
        //TODO() call function that we want to test [repository.getTopHeadlines()]

        //then
        //TODO() verify and assert is the actual result == expected?
    }

    @Test
    fun `return api error network`() = runBlocking {
        //given
        //TODO() mock data here [expected data], stub data remote source.

        //when
        //TODO() call function that we want to test [repository.getTopHeadlines()]

        //then
        //TODO() verify and assert is the actual result == expected?
    }

    @Test
    fun `return api error general`() = runBlocking {
        //given
        //TODO() mock data here [expected data], stub data remote source.

        //when
        //TODO() call function that we want to test [repository.getTopHeadlines()]

        //then
        //TODO() verify and assert is the actual result == expected?
    }
}