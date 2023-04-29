package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.auth.ChangePasswordUseCase
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _changePasswordState = MutableStateFlow<DataState<Unit>>(DataState.Idle)
    val changePasswordState: StateFlow<DataState<Unit>> = _changePasswordState.asStateFlow()

    private val _userState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val userState: StateFlow<DataState<User>> = _userState.asStateFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        readUserUseCase.execute()
            .onStart { _userState.emit(DataState.Loading) }
            .collect() { result ->
                when (result) {
                    is Result.Success -> {
                        _userState.emit(DataState.Success(result.data))
                    }

                    is Result.Error -> {
                        _userState.emit(DataState.Error(result.exception.message))
                    }
                }
            }
    }

    fun changePassword(email: String, currentPassword: String, newPassword: String) =
        viewModelScope.launch {
            changePasswordUseCase.execute(email, currentPassword, newPassword)
                .onStart {
                    _changePasswordState.emit(DataState.Loading)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> _changePasswordState.emit(DataState.Success(Unit))
                        is Result.Error -> _changePasswordState.emit(
                            DataState.Error(
                                result.exception.message
                            )
                        )
                    }
                }
        }
}