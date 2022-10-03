package com.tkpd.devcamp2022

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact.ContactDataSource
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact.ContactDataSourceImpl
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.util.PhoneNumberFormatter
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewmodel.ContactBookViewModel2
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import junit.framework.Assert
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactBookViewModel2bTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule: MainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ContactBookViewModel2

    // Declare contactDataSource and annotate using @MockK
    @MockK
    private lateinit var contactDataSource: ContactDataSource

    @Before
    fun setup() {
        // Initialize mocked ContactDataSource
        contactDataSource = mockk<ContactDataSourceImpl>()

        // Inject contactDataSource into ContactBookViewModel2
        viewModel = ContactBookViewModel2(contactDataSource, UnconfinedTestDispatcher())
    }

    @Test
    fun `getContactList should call formatPrefixClientNumber and validatePrefixClientNumber`() {
        // Given
        val data = mutableListOf(
            Contact("Misael", "081208120812"),
            Contact("Kalian", "085708570857"),
        )
        // Mock the PhoneNumberFormatter object
        mockkObject(PhoneNumberFormatter)

        // When
        every { contactDataSource.getContactList() } returns data
        every { PhoneNumberFormatter.formatPrefixPhoneNumber(any()) } returns "081208120812"
        viewModel.getContactList()

        // Then
        verify { PhoneNumberFormatter.formatPrefixPhoneNumber(any()) }
    }

    @Test
    fun `getContactList should only call formatPrefixClientNumber for non-empty contact numbers`() {
        // Given
        val data = mutableListOf(
            Contact("Misael", "081208120812"),
            Contact("Kalian", ""),
            Contact("Kita", "085708570857"),
            Contact("Tokopedia", ""),
        )
        // Mock the PhoneNumberFormatter object
        mockkObject(PhoneNumberFormatter)

        // When
        every { contactDataSource.getContactList() } returns data
        every { PhoneNumberFormatter.formatPrefixPhoneNumber(any()) } returns "081208120812"
        viewModel.getContactList()

        // Then
        verify(exactly = 2) { PhoneNumberFormatter.formatPrefixPhoneNumber(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getDelayedContactList should return the correct data`() = runTest {
        // Given
        val data = mutableListOf(
            Contact("Misael", "081208120812"),
            Contact("Kalian", "085708570857"),
        )

        // When
        every { contactDataSource.getContactList() } returns data
        viewModel.getDelayedContactList()
        advanceUntilIdle()

        // Then
        val result = viewModel.contacts.value
        assertNotNull(result)

        assert(result?.any { it.name == "Misael" } == true)
        assertTrue(result?.any { it.name == "Kalian" } == true)
        assertTrue(result?.any { it.number == "081208120812" } == true)
        assertTrue(result?.any { it.number == "085708570857" } == true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getDelayedContactListWithContext should return the correct data`() = runTest {
        // Given
        val data = mutableListOf(
            Contact("Misael", "081208120812"),
            Contact("Kalian", "085708570857"),
        )

        // When
        every { contactDataSource.getContactList() } returns data
        viewModel.getDelayedContactListWithContext()
        advanceUntilIdle()

        // Then
        val result = viewModel.contacts.value
        assertNotNull(result)

        assert(result?.any { it.name == "Misael" } == true)
        assertTrue(result?.any { it.name == "Kalian" } == true)
        assertTrue(result?.any { it.number == "081208120812" } == true)
        assertTrue(result?.any { it.number == "085708570857" } == true)
    }
}