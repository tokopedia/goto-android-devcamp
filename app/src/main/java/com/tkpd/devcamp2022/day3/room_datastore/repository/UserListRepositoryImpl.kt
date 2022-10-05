package com.tkpd.devcamp2022.day3.room_datastore.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tkpd.devcamp2022.day3.room_datastore.api.userlist.UserListApiService
import com.tkpd.devcamp2022.day3.room_datastore.db.dao.CachedUsersListDao
import com.tkpd.devcamp2022.day3.room_datastore.db.model.CachedUsersList
import com.tkpd.devcamp2022.day3.room_datastore.repository.state.UserListState
import com.tkpd.devcamp2022.day3.room_datastore.model.UsersList
import java.io.IOException
import java.net.SocketTimeoutException
import retrofit2.Response

class UserListRepositoryImpl(
    private val service: UserListApiService,
    private val database: CachedUsersListDao
): UserListRepository {

    override suspend fun getUserList(isFromCached: Boolean): UserListState {
        return if (isFromCached){
            responseStateUserListFromCached()
        } else {
            responseStateUserList(service.getUserList())
        }
    }

    private fun responseStateUserList(result: Response<UsersList>): UserListState {
        return try {
            if (result.isSuccessful && result.body() != null) {
                    val userListResponse = result.body() as UsersList
                    cachedResponse(result)
                    UserListState.SuccessGetUserList(userListResponse)
            } else {
                UserListState.ErrorGetUserList(IOException("Error Fetching"))
            }
        } catch (e: SocketTimeoutException) {
            UserListState.ErrorGetUserList(IOException("Time Out"))
        }
    }

    private fun responseStateUserListFromCached(): UserListState {
        val jsonResponse = database.load()
        val userListResponse = Gson().fromJson(jsonResponse, UsersList::class.java)
        return UserListState.SuccessGetUserList(userListResponse)
    }

    private fun cachedResponse(result: Response<UsersList>) {
        val gsonBuilder = GsonBuilder().create()
        val jsonBuiled = gsonBuilder.toJson(result.body())
        val responseCached =
            CachedUsersList(null, jsonBuiled)

        database.delete()
        database.save(responseCached)
    }
}