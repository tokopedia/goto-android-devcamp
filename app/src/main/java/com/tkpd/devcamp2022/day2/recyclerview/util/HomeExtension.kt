package com.tkpd.devcamp2022.day2.recyclerview.util

import org.json.JSONArray
import org.json.JSONException

@Throws(JSONException::class)
fun String.toJsonArray(): JSONArray {
    return JSONArray(this)
}