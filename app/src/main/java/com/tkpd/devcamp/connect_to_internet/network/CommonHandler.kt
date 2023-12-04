package com.tkpd.devcamp.connect_to_internet.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.DateTypeAdapter
import com.google.gson.stream.MalformedJsonException
import java.io.IOException
import java.util.Date
import okhttp3.ResponseBody
import org.json.JSONException

sealed class ApiErrorType {
    object UNKNOWN : ApiErrorType() // default
    object NETWORK : ApiErrorType() // internet
    object SERVER : ApiErrorType() // 5xx
    object AUTH : ApiErrorType() // 401

    // 4xx
    class CLIENT(
        val errors: List<Error>,
        val data: List<Error>? = null,
    ) : ApiErrorType()
}

sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val apiErrorType: ApiErrorType) : ApiResult<Nothing>() {
        companion object {
            operator fun invoke(e: Exception): Error {
                return Error(getApiError(e))
            }
        }
    }
}


fun getApiError(e: Throwable): ApiErrorType {
    data class ErrorResponse(
        @SerializedName("data") val data: Any? = null,
        @SerializedName("errors") val errors: List<Error>,
        @SerializedName("retryable") val retryable: Boolean = false
    ) {

        fun getErrorDataString(): String = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .create()
            .toJson(data, Map::class.java)
    }

    fun parseError(responseBody: ResponseBody): ErrorResponse? {
        var errorResponse: ErrorResponse? = null
        try {
            errorResponse = Gson().fromJson(responseBody.string(), ErrorResponse::class.java)
        } catch (e: IOException) {
            // TODO handle something
        } catch (e: JSONException) {
            // TODO handle something
        } catch (e: Exception) {
            // TODO handle something
        }
        return errorResponse
    }

    return when (e) {
        is MalformedJsonException -> ApiErrorType.SERVER
        is IOException -> ApiErrorType.NETWORK
        is retrofit2.HttpException -> {
            when (e.code()) {
                401 -> {
                    ApiErrorType.AUTH
                }

                in 400..499 -> {
                    val parsedErrorResponse = e.response()?.errorBody()?.let { parseError(it) }
                    var errors: List<Error>? = null
                    if (parsedErrorResponse?.errors != null) {
                        errors = parsedErrorResponse.errors
                    }
                    if (errors?.isNotEmpty() == true) {
                        ApiErrorType.CLIENT(errors, errors)
                    } else {
                        ApiErrorType.SERVER
                    }
                }

                in 500..599 -> {
                    ApiErrorType.SERVER
                }

                else -> throw e
            }
        }

        else -> ApiErrorType.UNKNOWN
    }
}


@Suppress("TooGenericExceptionCaught")
inline fun <T : Any> safeApiCall(
    block: () -> T
): ApiResult<T> {
    return try {
        ApiResult.Success(block())
    } catch (e: Exception) {
        ApiResult.Error(e)
    }
}
