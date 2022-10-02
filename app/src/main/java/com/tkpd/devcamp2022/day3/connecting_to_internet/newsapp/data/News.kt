package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    var title: String?,
    var sourceName: String?,
    var author: String?,
    var url: String?,
    var urlImage: String?
) : Parcelable