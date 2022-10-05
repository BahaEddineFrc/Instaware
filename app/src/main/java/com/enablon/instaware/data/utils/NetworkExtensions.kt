package com.enablon.instaware.data.utils

import com.enablon.instaware.common.AppResult
import com.enablon.instaware.common.UNKNOWN_ERROR_MSG
import com.enablon.instaware.common.utils.loge
import com.enablon.instaware.common.utils.logi
import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.parseServerResponse(): AppResult<T?> {
    val classTypeName: String = ""
        //(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].typeName

    return if (isSuccessful) {
        logi { "$classTypeName succeeded > ${body()}" }
        AppResult.Success(body())
    } else {
        try {
            val jsonError = JSONObject(errorBody()!!.string())
            loge { "$classTypeName failed > $jsonError" }
            AppResult.Error(jsonError.toString())
        } catch (e: Exception) {
            loge { "$classTypeName parse response error failed > $e" }
            AppResult.Error(UNKNOWN_ERROR_MSG)
        }
    }
}
