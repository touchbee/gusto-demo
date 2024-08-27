package com.gusto.lunchmenu.data.common

sealed class DataState<T> {
    class Loading<T> : DataState<T>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error<T>(val error: Throwable) : DataState<T>()
}