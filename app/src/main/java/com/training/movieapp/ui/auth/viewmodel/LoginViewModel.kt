package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.domain.usecase.LoginUseCase
import com.training.movieapp.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private val _loginState = MutableSharedFlow<OperationState<User>>(replay = 0)
    val loginState: SharedFlow<OperationState<User>> = _loginState.asSharedFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        loginUseCase.login(email, password)
            .onStart {
                _loginState.emit(OperationState.Loading)
            }
            .collect { result ->
                when (result) {
                    is Result.Success -> _loginState.emit(OperationState.Success(result.data))
                    is Result.Error -> _loginState.emit(OperationState.Error(result.exception.message))
                }
            }
    }

    fun saveUser(user: User) = viewModelScope.launch {
        saveUserUseCase.saveUser(user)
    }
}