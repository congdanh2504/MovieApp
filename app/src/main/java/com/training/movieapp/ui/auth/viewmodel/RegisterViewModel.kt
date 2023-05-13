package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    BaseViewModel() {
    private val _registerState = MutableStateFlow<DataState<Unit>>(DataState.Idle)
    val registerState: StateFlow<DataState<Unit>> = _registerState.asStateFlow()

    fun register(email: String, username: String, password: String) = viewModelScope.launch {
        handleState(_registerState, registerUseCase.execute(email, username, password))
    }
}
