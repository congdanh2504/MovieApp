package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.domain.usecase.auth.ChangePasswordUseCase
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val readUserUseCase: ReadUserUseCase
) :
    ViewModel() {
    private val _changePasswordState = MutableStateFlow<OperationState<Unit>>(OperationState.Idle)
    val changePasswordState: StateFlow<OperationState<Unit>> = _changePasswordState.asStateFlow()

    private val _user = MutableSharedFlow<User>(replay = 1)
    val user: SharedFlow<User> = _user.asSharedFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        readUserUseCase.execute().collect() { user ->
            _user.emit(user)
        }
    }

    fun changePassword(email: String, currentPassword: String, newPassword: String) =
        viewModelScope.launch {
            changePasswordUseCase.execute(email, currentPassword, newPassword)
                .onStart {
                    _changePasswordState.emit(OperationState.Loading)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> _changePasswordState.emit(OperationState.Success(Unit))
                        is Result.Error -> _changePasswordState.emit(
                            OperationState.Error(
                                result.exception.message
                            )
                        )
                    }
                }
        }
}