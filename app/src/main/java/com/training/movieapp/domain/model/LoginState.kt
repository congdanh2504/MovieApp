package com.training.movieapp.domain.model

sealed class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String?) : LoginState()
}