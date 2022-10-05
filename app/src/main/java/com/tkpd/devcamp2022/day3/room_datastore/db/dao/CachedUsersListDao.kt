package com.tkpd.devcamp2022.day3.room_datastore.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tkpd.devcamp2022.day3.room_datastore.db.model.CachedUsersList

@Dao
interface CachedUsersListDao {

    @Insert
    fun save(cachedUsersList: CachedUsersList)

    @Query("Select response from cached_user_list")
    fun load(): String

    @Query("DELETE FROM cached_user_list")
    fun delete()
}