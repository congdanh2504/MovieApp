package com.training.movieapp.domain.model.state

sealed class OperationState<out T> {
    object Idle : OperationState<Nothing>()
    object Loading : OperationState<Nothing>()
    data class Success<out T>(val data: T) : OperationState<T>()
    data class Error(val message: String?) : OperationState<Nothing>()
}