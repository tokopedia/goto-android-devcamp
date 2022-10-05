package com.tkpd.devcamp2022.day3.room_datastore.repository.state

import com.tkpd.devcamp2022.day3.room_datastore.model.UsersList

sealed class UserListState {
    data class ErrorGetUserList(val e: Exception): UserListState()
    data class SuccessGetUserList(val userListResponse: UsersList?): UserListState()
}