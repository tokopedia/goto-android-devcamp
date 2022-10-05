package com.tkpd.devcamp2022.day3.room_datastore.model

data class User(
    val id: String,
    val isLoggedIn: Boolean,
    val userName: String,
)