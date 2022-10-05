package com.tkpd.devcamp2022

import android.app.Application
import com.tkpd.devcamp2022.day3.room_datastore.db.dao.AppDatabase

class MainApplication: Application()  {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.buildDatabase(this) }
}