package com.enablon.instaware.data.utils

import com.enablon.instaware.common.AppResult
import com.enablon.instaware.common.UNKNOWN_ERROR_MSG
import com.enablon.instaware.common.utils.loge
import com.enablon.instaware.common.utils.logi
import org.json.JSONObject
import retrofit2.Response

/**
 * A response wrapping method that returns either the requested data or an error containing the failure message
 */
fun <T> Response<T>.parseServerResponse(): AppResult<T?> = if (isSuccessful) {
    logi { "API call succeeded > ${body()}" }
    AppResult.Success(body())
} else {
    try {
        val jsonError = JSONObject(errorBody()!!.string())
        loge { "API call failed > $jsonError" }
        AppResult.Error(jsonError.toString())
    } catch (e: Exception) {
        loge { "API call parse response error failed > $e" }
        AppResult.Error(UNKNOWN_ERROR_MSG)
    }
}

