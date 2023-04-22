package com.training.movieapp.domain.model.state

sealed class ChangePasswordState {
    object Loading : ChangePasswordState()
    object Success : ChangePasswordState()
    data class Error(val message: String?) : ChangePasswordState()
}