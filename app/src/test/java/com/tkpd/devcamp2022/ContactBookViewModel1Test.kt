package com.tkpd.devcamp2022

import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewmodel.ContactBookViewModel1
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ContactBookViewModel1Test {

    private lateinit var viewModel: ContactBookViewModel1
    private lateinit var data: List<Contact>

    @Before
    fun setup() {
        viewModel = ContactBookViewModel1()
        data = viewModel.getContactList()
    }

    @Test
    fun `getContactList returns the correct contact data`() {
        assertTrue(data.any { it.name == "Misael"} )
        assertTrue(data.any { it.name == "Kalian"} )
        assertTrue(data.any { it.number == "081208120812"} )
        assertTrue(data.any { it.number == "085708570857"} )
    }

    @Test
    fun `getContactList returns a well formatted contact number`() {
        assertTrue(data.all { it.number.startsWith("0") })
    }
}