package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.domain.usecase.ReadUserUseCase
import com.training.movieapp.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val readUserUseCase: ReadUserUseCase
) :
    ViewModel() {
    private val _signOutState = MutableSharedFlow<OperationState<Unit>>(replay = 0)
    val signOutState: SharedFlow<OperationState<Unit>> = _signOutState.asSharedFlow()

    private val _user = MutableSharedFlow<User>(replay = 1)
    val user: SharedFlow<User> = _user.asSharedFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        readUserUseCase.readUser().collect() { user ->
            _user.emit(user)
        }
    }

    fun signOut() = viewModelScope.launch {
        signOutUseCase.signOut()
            .collect { result ->
                when (result) {
                    is Result.Success -> _signOutState.emit(OperationState.Success(Unit))
                    is Result.Error -> _signOutState.emit(OperationState.Error(result.exception.message))
                }
            }
    }
}