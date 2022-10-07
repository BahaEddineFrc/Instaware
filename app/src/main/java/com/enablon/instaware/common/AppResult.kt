package com.enablon.instaware.common

/**
 * A network response wrapper to handle communication between the usecase and the viewModel
 */
sealed class AppResult<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : AppResult<T>(data)
    class Success<T>(data: T) : AppResult<T>(data)
    class Error<T>(message: String, data: T? = null) : AppResult<T>(data, message)
}