package com.tkpd.devcamp2022.day3.room_datastore.repository

import com.tkpd.devcamp2022.day3.room_datastore.api.userlist.UserListApiService
import com.tkpd.devcamp2022.day3.room_datastore.domain.UserListState
import com.tkpd.devcamp2022.day3.room_datastore.model.UsersList
import java.io.IOException
import java.net.SocketTimeoutException
import retrofit2.Response

class UserListRepositoryImpl(private val service: UserListApiService): UserListRepository {

    override suspend fun getUserList(): UserListState {
        return responseStateUserList(service.getUserList())
    }

    private fun responseStateUserList(result: Response<UsersList>): UserListState {
        return try {
            if (result.isSuccessful && result.body() != null) {
                    val userListResponse = result.body() as UsersList
                    UserListState.SuccessGetUserList(userListResponse)
            } else {
                UserListState.ErrorGetUserList(IOException("Error Fetching"))
            }
        } catch (e: SocketTimeoutException) {
            UserListState.ErrorGetUserList(IOException("Time Out"))
        }
    }
}