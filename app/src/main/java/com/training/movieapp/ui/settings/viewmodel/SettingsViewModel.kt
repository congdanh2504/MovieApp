package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.auth.SignOutUseCase
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val readUserUseCase: ReadUserUseCase
) :
    BaseViewModel() {
    private val _signOutState = MutableStateFlow<DataState<Unit>>(DataState.Idle)
    val signOutState: StateFlow<DataState<Unit>> = _signOutState.asStateFlow()

    private val _userState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val userState: StateFlow<DataState<User>> = _userState.asStateFlow()

    fun readUser() = viewModelScope.launch {
        handleState(_userState, readUserUseCase.execute())
    }

    fun signOut() = viewModelScope.launch {
        handleState(_signOutState, signOutUseCase.execute())
    }
}
