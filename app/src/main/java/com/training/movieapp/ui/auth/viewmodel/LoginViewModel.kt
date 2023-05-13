package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.auth.LoginUseCase
import com.training.movieapp.domain.usecase.datastore.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : BaseViewModel() {
    private val _loginState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val loginState: StateFlow<DataState<User>> = _loginState.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        handleState(_loginState, loginUseCase.execute(email, password))
    }

    suspend fun saveUser(user: User) = saveUserUseCase.execute(user)
}
