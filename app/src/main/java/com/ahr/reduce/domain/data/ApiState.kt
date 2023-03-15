package com.ahr.reduce.domain.data

sealed interface ApiState<out T> {
    object Loading : ApiState<Nothing>
    data class Success<out T>(val data: T) : ApiState<T>
    data class Error(val exception: Throwable) : ApiState<Nothing>
}
