package com.tkpd.devcamp2022.day3.room_datastore.repository

import com.tkpd.devcamp2022.day3.room_datastore.repository.state.UserListState

interface UserListRepository {
    //TODO(1,5) - Add isFromCached
    suspend fun getUserList(): UserListState
}