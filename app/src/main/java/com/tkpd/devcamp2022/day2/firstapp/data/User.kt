package com.tkpd.devcamp2022.day2.firstapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val name: String,
    val age: Int,
): Parcelable