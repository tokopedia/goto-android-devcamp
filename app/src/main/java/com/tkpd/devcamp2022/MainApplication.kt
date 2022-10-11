package com.tkpd.devcamp2022

import android.app.Application

//TODO(1,4) - Initialize lazy db
class MainApplication: Application()  {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
//    val database by lazy { AppDatabase.buildDatabase(this) }
}