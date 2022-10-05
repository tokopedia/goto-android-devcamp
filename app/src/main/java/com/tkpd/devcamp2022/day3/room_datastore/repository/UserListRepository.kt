package com.tkpd.devcamp2022.day3.room_datastore.repository

import com.tkpd.devcamp2022.day3.room_datastore.domain.UserListState

interface UserListRepository {
    suspend fun getUserList(): UserListState
}