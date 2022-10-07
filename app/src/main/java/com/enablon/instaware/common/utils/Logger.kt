package com.enablon.instaware.common.utils

import android.util.Log

const val GLOBAL_LOG_TAG = "ENABLON_INSTAWARE_LOGGER"
const val CUSTOM_ERROR_TAG = "ENABLON_INSTAWARE_ERROR"

/**
 * Easy accessible Logging methods
 */
inline fun logFormatter(
    caller: Any,
    message: () -> String,
    logMethod: (String, String) -> Unit,
    tagPrefix: String = GLOBAL_LOG_TAG,
) {
    val formattedTag = "$tagPrefix - class ${caller::class.java.simpleName}"
    val formattedMessage =
        if (Thread.currentThread().stackTrace[2] != null)
            "method ${Thread.currentThread().stackTrace[2].methodName}: ${message()}"
        else message()
    logMethod(formattedTag, formattedMessage)
}

inline fun Any.logd(message: () -> String) {
    logFormatter(this, message, Log::d)
}

inline fun Any.logi(message: () -> String) {
    logFormatter(this, message, Log::i)
}

inline fun Any.loge(message: () -> String) {
    logFormatter(this, message, Log::e, CUSTOM_ERROR_TAG)
}