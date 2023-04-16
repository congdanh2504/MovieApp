package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.AuthState
import com.training.movieapp.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState
    private val _resetPasswordState = MutableLiveData<Result<Unit>>()
    val resetPasswordState: LiveData<Result<Unit>> = _resetPasswordState

    fun register(email: String, username: String, password: String) = viewModelScope.launch {
        _authState.value = AuthState.Loading
        when (val result = authUseCase.register(email, username, password)) {
            is Result.Success -> _authState.value = AuthState.Authenticated
            is Result.Error -> _authState.value = AuthState.Error(result.exception.message)
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _authState.value = AuthState.Loading
        when (val result = authUseCase.login(email, password)) {
            is Result.Success -> _authState.value = AuthState.Authenticated
            is Result.Error -> _authState.value = AuthState.Error(result.exception.message)
        }
    }

    fun signOut() = viewModelScope.launch {
        _authState.value = AuthState.Loading
        when (val result = authUseCase.signOut()) {
            is Result.Success -> _authState.value = AuthState.UnAuthenticated
            is Result.Error -> _authState.value = AuthState.Error(result.exception.message)
        }
    }

    fun resetPassword(email: String) = viewModelScope.launch {
        _resetPasswordState.value = authUseCase.resetPassword(email)
//        when (val result = authUseCase.resetPassword(email)) {
//            is Result.Success -> _authState.value = AuthState.UnAuthenticated
//            is Result.Error -> _authState.value = AuthState.Error(result.exception.message)
//        }
    }
}