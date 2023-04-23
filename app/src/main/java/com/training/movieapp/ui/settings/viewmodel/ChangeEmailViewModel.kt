package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.domain.usecase.auth.ChangeEmailUseCase
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import com.training.movieapp.domain.usecase.datastore.SaveUserUseCase
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
class ChangeEmailViewModel @Inject constructor(
    private val changeEmailUseCase: ChangeEmailUseCase,
    private val readUserUseCase: ReadUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) :
    ViewModel() {
    private val _changeEmailState = MutableStateFlow<OperationState<User>>(OperationState.Idle)
    val changeEmailState: StateFlow<OperationState<User>> = _changeEmailState.asStateFlow()

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

    fun changeEmail(email: String, currentPassword: String, newEmail: String) =
        viewModelScope.launch {
            changeEmailUseCase.execute(email, currentPassword, newEmail)
                .onStart {
                    _changeEmailState.emit(OperationState.Loading)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> _changeEmailState.emit(OperationState.Success(result.data))
                        is Result.Error -> _changeEmailState.emit(
                            OperationState.Error(
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