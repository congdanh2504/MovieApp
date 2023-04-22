package com.training.movieapp.domain.model.state

import com.training.movieapp.domain.model.User

sealed class UpdateProfileState {
    object Loading : UpdateProfileState()
    data class Success(val user: User) : UpdateProfileState()
    data class Error(val message: String?) : UpdateProfileState()
}