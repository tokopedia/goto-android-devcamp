package com.tkpd.devcamp2022.day3.room_datastore.api

import com.tkpd.devcamp2022.day3.room_datastore.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MockUserApi: UserApi {

    override suspend fun loginApi(userName: String, password: String): User = withContext(Dispatchers.IO) {
        Thread.sleep(3000)
        return@withContext createMockUser(userName, password)
    }

    private fun createMockUser(userName: String, password: String): User {
        return User(
            (0..1000).random().toString(),
            password.isNotEmpty(),
            userName
        )
    }
}