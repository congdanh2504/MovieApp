package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.domain.usecase.auth.LoginUseCase
import com.training.movieapp.domain.usecase.datastore.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<OperationState<User>>(OperationState.Idle)
    val loginState: StateFlow<OperationState<User>> = _loginState.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        loginUseCase.execute(email, password)
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
        saveUserUseCase.execute(user)
    }
}