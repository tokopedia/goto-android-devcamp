package com.tkpd.devcamp2022.day3.room_datastore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.tkpd.devcamp2022.UserStore
import com.tkpd.devcamp2022.day3.room_datastore.model.User
import kotlinx.coroutines.flow.map

//TODO(3,3) - Create Accessor for Proto DataStore
private const val USER_DATA_STORE_FILE_NAME = "user_store.pb"
private val Context.userDataStore : DataStore<UserStore> by dataStore(
    fileName = USER_DATA_STORE_FILE_NAME,
    serializer = UserStoreSerializer
)

class UserProtoDataStoreManager(val context: Context) {

    //TODO(3,4) - Get Data from Proto DataStore
    fun getUserProtoDataStore() = context.userDataStore.data.map {
        User(
            id = it.id,
            isLoggedIn = it.isLoggedIn,
            userName = it.userName
        )
    }

    //TODO(3,5) - Save Data to Proto DataStore
    suspend fun saveToProtoDataStore(user: User) {
        context.userDataStore.updateData { userDataStore ->
            userDataStore.toBuilder()
                .setId(user.id)
                .setIsLoggedIn(user.isLoggedIn)
                .setUserName(user.userName)
                .build()
        }
    }

    //TODO(3,6) - Delete Data from Proto DataStore
    suspend fun clearUserProtoDataStore() {
        context.userDataStore.updateData { userDataStore ->
            userDataStore.toBuilder().clear().build()
        }
    }
}