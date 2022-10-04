package com.tkpd.devcamp2022.day3.room_datastore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.tkpd.devcamp2022.UserStore
import com.tkpd.devcamp2022.day3.room_datastore.model.User
import kotlinx.coroutines.flow.map

private const val USER_DATA_STORE_FILE_NAME = "user_store.pb"
private val Context.userDataStore : DataStore<UserStore> by dataStore(
    fileName = USER_DATA_STORE_FILE_NAME,
    serializer = UserStoreSerializer
)

class UserProtoDataStoreManager(val context: Context) {

    suspend fun saveToProtoDataStore(user: User) {
        context.userDataStore.updateData { userDataStore ->
            userDataStore.toBuilder()
                .setId(user.id)
                .setIsLoggedIn(user.isLoggedIn)
                .setUserName(user.userName)
                .build()
        }
    }

    suspend fun getUserProtoDataStore() = context.userDataStore.data.map {
        User(
            id = it.id,
            isLoggedIn = it.isLoggedIn,
            userName = it.userName
        )
    }

    suspend fun clearUserProtoDataStore() {
        context.userDataStore.updateData { userDataStore ->
            userDataStore.toBuilder().clear().build()
        }
    }
}