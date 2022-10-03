package com.tkpd.devcamp2022.day3.room_datastore.api

import com.tkpd.devcamp2022.day3.room_datastore.model.User

interface UserApi {
    suspend fun loginApi(userName: String, password: String): User
}