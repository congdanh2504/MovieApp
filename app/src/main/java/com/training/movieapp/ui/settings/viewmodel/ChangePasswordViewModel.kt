package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.ChangePasswordState
import com.training.movieapp.domain.model.state.LoginState
import com.training.movieapp.domain.usecase.ChangePasswordUseCase
import com.training.movieapp.domain.usecase.ReadUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val readUserUseCase: ReadUserUseCase
) :
    ViewModel() {
    private val _changePasswordState = MutableSharedFlow<ChangePasswordState>(replay = 0)
    val changePasswordState: SharedFlow<ChangePasswordState> = _changePasswordState.asSharedFlow()

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

    fun changePassword(email: String, currentPassword: String, newPassword: String) =
        viewModelScope.launch {
            changePasswordUseCase.changePassword(email, currentPassword, newPassword)
                .onStart {
                    _changePasswordState.emit(ChangePasswordState.Loading)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> _changePasswordState.emit(ChangePasswordState.Success)
                        is Result.Error -> _changePasswordState.emit(
                            ChangePasswordState.Error(
                                result.exception.message
                            )
                        )
                    }
                }
        }
}