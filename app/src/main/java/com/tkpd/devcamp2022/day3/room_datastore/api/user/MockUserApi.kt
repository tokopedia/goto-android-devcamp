package com.tkpd.devcamp2022.day3.room_datastore.api.user

import com.tkpd.devcamp2022.day3.room_datastore.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MockUserApi: UserApi {

    companion object {
        const val TIMER = 3000L
        const val MIN_ID_RANDOM = 0
        const val MAX_ID_RANDOM = 1000
    }

    override suspend fun loginApi(userName: String, password: String): User = withContext(Dispatchers.IO) {
        Thread.sleep(TIMER)
        return@withContext createMockUser(userName, password)
    }

    private fun createMockUser(userName: String, password: String): User {
        return User(
            (MIN_ID_RANDOM..MAX_ID_RANDOM).random().toString(),
            password.isNotEmpty(),
            userName
        )
    }
}