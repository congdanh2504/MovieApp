package com.training.movieapp.domain.model.state

sealed class SignOutState {
    object Success : SignOutState()
    data class Error(val message: String?) : SignOutState()
}