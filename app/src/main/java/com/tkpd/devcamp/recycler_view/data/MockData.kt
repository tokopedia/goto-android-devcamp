package com.tkpd.devcamp.recycler_view.data

import android.content.Context
import com.google.gson.Gson
import com.tkpd.devcamp.recycler_view.model.NewsResponseModel
import com.tkpd.devcamp.recycler_view.utils.readJsonFromAssets

fun getMockNewsData(context: Context): NewsResponseModel {
    val jsonString = readJsonFromAssets(context)
    return Gson().fromJson(jsonString, NewsResponseModel::class.java)
}