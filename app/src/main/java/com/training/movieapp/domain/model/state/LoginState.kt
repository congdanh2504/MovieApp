package com.training.movieapp.domain.model.state

import com.training.movieapp.domain.model.User

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String?) : LoginState()
}