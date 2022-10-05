package com.tkpd.devcamp2022.day3.room_datastore.model

import com.google.gson.annotations.SerializedName

data class UsersList(
    @field:SerializedName("page")
    val page: Int? = null,
    @field:SerializedName("per_page")
    val perPage: Int? = null,
    @field:SerializedName("total")
    val total: Int? = null,
    @field:SerializedName("total_pages")
    val totalPages: Int? = null,
    @field:SerializedName("data")
    val data: List<UserData> = emptyList()
)

data class UserData(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("first_name")
    val firstName: String? = null,
    @field:SerializedName("last_name")
    val lastName: String? = null,
    @field:SerializedName("avatar")
    val avatar: String? = null,
)
