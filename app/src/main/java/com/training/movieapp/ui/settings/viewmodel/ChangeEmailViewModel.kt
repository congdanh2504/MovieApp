package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.auth.ChangeEmailUseCase
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import com.training.movieapp.domain.usecase.datastore.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeEmailViewModel @Inject constructor(
    private val changeEmailUseCase: ChangeEmailUseCase,
    private val readUserUseCase: ReadUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) :
    BaseViewModel() {
    private val _changeEmailState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val changeEmailState: StateFlow<DataState<User>> = _changeEmailState.asStateFlow()

    private val _userState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val userState: StateFlow<DataState<User>> = _userState.asStateFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        handleState(_userState, readUserUseCase.execute())
    }

    fun changeEmail(email: String, currentPassword: String, newEmail: String) =
        viewModelScope.launch {
            handleState(
                _changeEmailState,
                changeEmailUseCase.execute(email, currentPassword, newEmail)
            )
        }

    fun saveUser(user: User) = viewModelScope.launch {
        saveUserUseCase.execute(user)
    }
}
