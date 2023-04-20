package com.training.movieapp.domain.model

sealed class RegisterState {
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String?) : RegisterState()
}