package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
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
    private val _loginState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val loginState: StateFlow<DataState<User>> = _loginState.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        loginUseCase.execute(email, password)
            .onStart {
                _loginState.emit(DataState.Loading)
            }
            .collect { result ->
                when (result) {
                    is Result.Success -> _loginState.emit(DataState.Success(result.data))
                    is Result.Error -> _loginState.emit(DataState.Error(result.exception.message))
                }
            }
    }

    suspend fun saveUser(user: User) = saveUserUseCase.execute(user)
}
