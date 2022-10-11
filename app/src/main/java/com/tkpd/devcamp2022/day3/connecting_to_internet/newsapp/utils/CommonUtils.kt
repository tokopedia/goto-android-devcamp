package com.tkpd.devcamp2022.day3.connecting_to_internet.newsapp.utils

import android.content.Context
import java.io.IOException

/**
 * Created by irpan on 02/10/22.
 */
object CommonUtils {
    fun getAssetJsonData(context: Context, jsonFileName: String): String? {
        var json: String? = null
        json = try {
            context.assets.open(jsonFileName).bufferedReader().use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}