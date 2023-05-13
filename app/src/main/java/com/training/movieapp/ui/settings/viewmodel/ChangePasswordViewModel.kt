package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.auth.ChangePasswordUseCase
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val readUserUseCase: ReadUserUseCase
) :
    BaseViewModel() {
    private val _changePasswordState = MutableStateFlow<DataState<Unit>>(DataState.Idle)
    val changePasswordState: StateFlow<DataState<Unit>> = _changePasswordState.asStateFlow()

    private val _userState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val userState: StateFlow<DataState<User>> = _userState.asStateFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        handleState(_userState, readUserUseCase.execute())
    }

    fun changePassword(email: String, currentPassword: String, newPassword: String) =
        viewModelScope.launch {
            handleState(
                _changePasswordState,
                changePasswordUseCase.execute(email, currentPassword, newPassword)
            )
        }
}
