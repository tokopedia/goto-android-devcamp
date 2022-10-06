package com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact

class ContactBookViewModel1: ViewModel() {

    fun getContactList(): List<Contact> {
        return listOf(
            Contact("Misael", "081208120812"),
            Contact("Kalian", "085708570857"),
        )
    }
}