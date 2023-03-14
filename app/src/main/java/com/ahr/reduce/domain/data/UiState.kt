package com.ahr.reduce.domain.data

sealed interface UiState<out T> {
    object Idle : UiState<Nothing>
    object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val exception: Throwable) : UiState<Nothing>
}
