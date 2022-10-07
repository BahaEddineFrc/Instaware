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
        val jsonSpecificMessage =
            if (jsonError.has("error"))
                if (jsonError.getJSONObject("error").has("message"))
                    jsonError.getJSONObject("error").get("message")
                else jsonError
            else jsonError

        loge { "API call failed > $jsonSpecificMessage" }
        AppResult.Error(jsonSpecificMessage.toString())
    } catch (e: Exception) {
        loge { "API call parse response error failed > $e" }
        AppResult.Error(UNKNOWN_ERROR_MSG)
    }
}

