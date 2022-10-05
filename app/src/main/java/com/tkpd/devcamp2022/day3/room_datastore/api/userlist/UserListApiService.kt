package com.tkpd.devcamp2022.day3.room_datastore.api.userlist

import com.tkpd.devcamp2022.day3.room_datastore.model.UsersList
import retrofit2.Response
import retrofit2.http.GET

interface UserListApiService {
    @GET("api/users")
    suspend fun getUserList(): Response<UsersList>
}