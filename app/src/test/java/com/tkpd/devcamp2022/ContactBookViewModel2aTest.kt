package com.tkpd.devcamp2022

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact.ContactDataSource
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact.ContactDataSourceImpl
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewmodel.ContactBookViewModel2
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/** No coroutine-related test cases, without using MainDispatcherRule */
class ContactBookViewModel2aTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ContactBookViewModel2

    // Declare contactDataSource and annotate using @MockK
    @MockK
    private lateinit var contactDataSource: ContactDataSource

    @Before
    fun setup() {
        // Initialize mocked ContactDataSource
        contactDataSource = mockk<ContactDataSourceImpl>()

        // Inject contactDataSource into ContactBookViewModel2
        viewModel = ContactBookViewModel2(contactDataSource)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getContactList returns the correct contact data`() {
        // Given
        val data = mutableListOf(
            Contact("Misael", "081208120812"),
            Contact("Kalian", "085708570857"),
        )

        // When
        every { contactDataSource.getContactList() } returns data
        viewModel.getContactList()

        // Then
        val result = viewModel.contacts.value
        assertNotNull(result)

        assertTrue(result?.any { it.name == "Misael" } == true)
        assertTrue(result?.any { it.name == "Kalian" } == true)
        assertTrue(result?.any { it.number == "081208120812" } == true)
        assertTrue(result?.any { it.number == "085708570857" } == true)
    }
}