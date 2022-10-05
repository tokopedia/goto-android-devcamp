package com.tkpd.devcamp2022.day3.room_datastore.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cached_user_list")
data class CachedUsersList(
    @PrimaryKey(autoGenerate = true) val cid:Int?,
    @ColumnInfo(name = "response") val response: String
)