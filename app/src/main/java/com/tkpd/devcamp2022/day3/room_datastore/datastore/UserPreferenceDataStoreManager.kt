package com.tkpd.devcamp2022.day3.room_datastore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tkpd.devcamp2022.day3.room_datastore.model.User
import kotlinx.coroutines.flow.map

//TODO(2,1) - Create Preference Datastore
private const val USER_DATA_STORE = "USER_DATASTORE"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATA_STORE)

class UserPreferenceDataStoreManager(val context: Context) {

    companion object {
        val USER_ID = stringPreferencesKey("USER_ID")
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
        val USERNAME = stringPreferencesKey("USERNAME")
    }

    //TODO(2,2) - Read Preference Datastore
    fun getUserPreferenceDataStore() = context.dataStore.data.map {
        User(
            id = it[USER_ID] ?: "",
            isLoggedIn = it[IS_LOGGED_IN] ?: false,
            userName = it[USERNAME] ?: ""
        )
    }

    //TODO(2,3) - Save Preference Datastore
    suspend fun saveToPreferenceDataStore(user: User) {
        context.dataStore.edit {
            it[USER_ID] = user.id
            it[IS_LOGGED_IN] = user.isLoggedIn
            it[USERNAME] = user.userName
        }
    }

    //TODO(2,4) - Delete Preference Datastore
    suspend fun clearUserPreferenceDataStore() {
        context.dataStore.edit {
            it.clear()
        }
    }
}