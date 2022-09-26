package com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact

import android.content.ContentResolver
import android.provider.ContactsContract
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact


class ContactDataSourceImpl(
    private val contentResolver: ContentResolver?
): ContactDataSource {
    private val contactsProjection: Array<out String> = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
    )

    override fun getContactList(): MutableList<Contact> {
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        val cursor = contentResolver?.query(uri, contactsProjection, null, null, sort)

        val contacts = mutableListOf<Contact>()

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                val name = cursor.getString(displayNameIndex) ?: ""
                val phoneNumber = cursor.getString(numberIndex) ?: ""

                if (phoneNumber.isNotEmpty()) {
                    contacts.add(Contact(name, formatPrefixClientNumber(phoneNumber)))
                }
            }
        }
        cursor?.close()

        return contacts
    }

    private fun formatPrefixClientNumber(phoneNumber: String?): String {
        phoneNumber?.run {
            if ("".equals(phoneNumber.trim { it <= ' ' }, ignoreCase = true)) {
                return phoneNumber
            }
            var phoneNumberWithPrefix = validatePrefixClientNumber(phoneNumber)
            if (!phoneNumberWithPrefix.startsWith("0")) {
                phoneNumberWithPrefix = "0$phoneNumber"
            }
            return phoneNumberWithPrefix
        }
        return ""
    }

    private fun validatePrefixClientNumber(phoneNumber: String): String {
        var phoneNumber = phoneNumber
        if (phoneNumber.startsWith("62")) {
            phoneNumber = phoneNumber.replaceFirst("62".toRegex(), "0")
        }
        if (phoneNumber.startsWith("+62")) {
            phoneNumber = phoneNumber.replace("+62", "0")
        }
        return phoneNumber.replace("[^0-9]+".toRegex(), "")
    }
}