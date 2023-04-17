package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.LoginState
import com.training.movieapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _loginState = MutableSharedFlow<LoginState>(replay = 0)
    val loginState: SharedFlow<LoginState> = _loginState.asSharedFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        loginUseCase.login(email, password)
            .onStart {
                _loginState.emit(LoginState.Loading)
            }
            .collect { result ->
                when (result) {
                    is Result.Success -> _loginState.emit(LoginState.Success)
                    is Result.Error -> _loginState.emit(LoginState.Error(result.exception.message))
                }
            }
    }
}