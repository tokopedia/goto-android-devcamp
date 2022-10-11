package com.tkpd.devcamp2022.day3.room_datastore.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tkpd.devcamp2022.day3.room_datastore.api.userlist.UserListApiService
import com.tkpd.devcamp2022.day3.room_datastore.repository.state.UserListState
import com.tkpd.devcamp2022.day3.room_datastore.model.UsersList
import java.io.IOException
import java.net.SocketTimeoutException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

//TODO(1,6) - Add Dao to Repository
class UserListRepositoryImpl(
    private val service: UserListApiService,
    //private val database: CachedUsersListDao
) : UserListRepository {

    override suspend fun getUserList() = withContext(Dispatchers.IO) {
        //TODO(1,6) - Add if from cached
//        return@withContext if (isFromCached) {
//            TODO(1,10) Use Load data from DB
//            responseStateUserListFromCached()
//        } else {
//            try {
//                responseStateUserList(service.getUserList())
//            } catch (e: SocketTimeoutException) {
//                UserListState.ErrorGetUserList(IOException("Time Out"))
//            } catch (e: Exception) {
//                UserListState.ErrorGetUserList(e)
//            }
//        }
        return@withContext try {
                responseStateUserList(service.getUserList())
            } catch (e: SocketTimeoutException) {
                UserListState.ErrorGetUserList(IOException("Time Out"))
            } catch (e: Exception) {
                UserListState.ErrorGetUserList(e)
            }
    }

    private fun responseStateUserList(result: Response<UsersList>): UserListState {
        return if (result.isSuccessful && result.body() != null) {
                val userListResponse = result.body() as UsersList
                //TODO(1,9) - Use Delete Data and Save Data from DB
                //cachedResponse(result)
                UserListState.SuccessGetUserList(userListResponse)
            } else {
                UserListState.ErrorGetUserList(IOException("Error Fetching"))
            }

    }

    //TODO(1,7) - Create Load data from DB
//    private fun responseStateUserListFromCached(): UserListState {
//        val jsonResponse = database.load()
//        val userListResponse = Gson().fromJson(jsonResponse, UsersList::class.java)
//        return UserListState.SuccessGetUserList(userListResponse)
//    }

    //TODO(1,8) - Create Delete Data and Save Data from DB
//    private fun cachedResponse(result: Response<UsersList>) {
//        val gsonBuilder = GsonBuilder().create()
//        val jsonBuiled = gsonBuilder.toJson(result.body())
//        val responseCached =
//            CachedUsersList(null, jsonBuiled)
//
//        database.delete()
//        database.save(responseCached)
//    }
}