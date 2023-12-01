package com.tkpd.devcamp.recycler_view.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun readJsonFromAssets(context: Context): String {
    val assetsPath = "news_mock_response.json"
    return try {
        val bufferedReader = BufferedReader(
            InputStreamReader(context.assets.open(assetsPath))
        )
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach {
                stringBuilder.append(it)
            }
        }
        stringBuilder.toString()
    } catch (e: Exception) {
        ""
    }
}