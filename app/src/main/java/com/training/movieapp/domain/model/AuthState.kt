package com.training.movieapp.domain.model

sealed class AuthState {
    object Authenticated : AuthState()
    object Loading : AuthState()
    object UnAuthenticated: AuthState()
    data class Error(val message: String?) : AuthState()
}