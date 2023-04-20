package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.RegisterState
import com.training.movieapp.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    private val _registerState = MutableSharedFlow<RegisterState>(replay = 0)
    val registerState: SharedFlow<RegisterState> = _registerState.asSharedFlow()

    fun register(email: String, username: String, password: String) = viewModelScope.launch {
        registerUseCase.register(email, username, password)
            .onStart {
                _registerState.emit(RegisterState.Loading)
            }
            .collect { result ->
                when (result) {
                    is Result.Success -> _registerState.emit(RegisterState.Success)
                    is Result.Error -> _registerState.emit(RegisterState.Error(result.exception.message))
                }
            }
    }
}