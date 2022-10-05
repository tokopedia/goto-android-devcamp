package com.tkpd.devcamp2022.day3.room_datastore.db.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tkpd.devcamp2022.day3.room_datastore.db.model.CachedUsersList

@Database(entities = [CachedUsersList::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userListDao(): CachedUsersListDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(
            LOCK
        ){
            instance
                ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "cached-user-list.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}