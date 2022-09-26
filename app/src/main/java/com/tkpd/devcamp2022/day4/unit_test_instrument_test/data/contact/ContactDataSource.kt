package com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact

import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact

interface ContactDataSource {
    fun getContactList(): MutableList<Contact>
}