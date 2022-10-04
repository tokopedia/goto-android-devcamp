package com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact.ContactDataSource
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.util.PhoneNumberFormatter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContactBookViewModel3(
    private val contactDataSource: ContactDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = dispatcher + job

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>>
        get() = _contacts

    fun getContactList() {
        val data = contactDataSource.getContactList()
        for (contact in data) {
            if (contact.number.isNotEmpty()) {
                contact.number = PhoneNumberFormatter
                    .formatPrefixPhoneNumber(contact.number)
            }
        }
        _contacts.value = data
    }

    fun getDelayedContactListWithContext(): Job =
        viewModelScope.launch(dispatcher) {
            val data = contactDataSource.getContactList()
            delay(1000)
            for (contact in data) {
                contact.number = PhoneNumberFormatter
                    .formatPrefixPhoneNumber(contact.number)
            }

            delay(1000)
            _contacts.postValue(data)
        }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun getFactory(contactDataSource: ContactDataSource) = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ContactBookViewModel3(contactDataSource) as T
            }
        }
    }
}