package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.auth.ChangeEmailUseCase
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import com.training.movieapp.domain.usecase.datastore.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeEmailViewModel @Inject constructor(
    private val changeEmailUseCase: ChangeEmailUseCase,
    private val readUserUseCase: ReadUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) :
    ViewModel() {
    private val _changeEmailState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val changeEmailState: StateFlow<DataState<User>> = _changeEmailState.asStateFlow()

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

    fun changeEmail(email: String, currentPassword: String, newEmail: String) =
        viewModelScope.launch {
            changeEmailUseCase.execute(email, currentPassword, newEmail)
                .onStart {
                    _changeEmailState.emit(DataState.Loading)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> _changeEmailState.emit(DataState.Success(result.data))
                        is Result.Error -> _changeEmailState.emit(
                            DataState.Error(
                                result.exception.message
                            )
                        )
                    }
                }
        }

    fun saveUser(user: User) = viewModelScope.launch {
        saveUserUseCase.execute(user)
    }
}