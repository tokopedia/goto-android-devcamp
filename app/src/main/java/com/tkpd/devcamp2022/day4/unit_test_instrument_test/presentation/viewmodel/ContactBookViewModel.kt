package com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact.ContactDataSource
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact

class ContactBookViewModel(
    private val contactDataSource: ContactDataSource
): ViewModel() {

    fun getContactList(): List<Contact> {
        return contactDataSource.getContactList()
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun getFactory(contactDataSource: ContactDataSource) = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ContactBookViewModel(contactDataSource) as T
            }
        }
    }
}