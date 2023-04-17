package com.training.movieapp.domain.model

sealed class ResetPasswordState {
    object Loading : ResetPasswordState()
    object EmailSent : ResetPasswordState()
    data class Error(val message: String?) : ResetPasswordState()
}