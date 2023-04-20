package com.training.movieapp.domain.model

sealed class SignOutState {
    object Success : SignOutState()
    data class Error(val message: String?) : SignOutState()
}