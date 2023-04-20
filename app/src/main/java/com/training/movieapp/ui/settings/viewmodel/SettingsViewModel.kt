package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.SignOutState
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
    private val _signOutState = MutableSharedFlow<SignOutState>(replay = 0)
    val signOutState: SharedFlow<SignOutState> = _signOutState.asSharedFlow()

    private val _user = MutableSharedFlow<User>(replay = 0)
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
                    is Result.Success -> _signOutState.emit(SignOutState.Success)
                    is Result.Error -> _signOutState.emit(SignOutState.Error(result.exception.message))
                }
            }
    }
}