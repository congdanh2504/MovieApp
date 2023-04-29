package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    private val _registerState = MutableStateFlow<DataState<Unit>>(DataState.Idle)
    val registerState: StateFlow<DataState<Unit>> = _registerState.asStateFlow()

    fun register(email: String, username: String, password: String) = viewModelScope.launch {
        registerUseCase.execute(email, username, password)
            .onStart {
                _registerState.emit(DataState.Loading)
            }
            .collect { result ->
                when (result) {
                    is Result.Success -> _registerState.emit(DataState.Success(Unit))
                    is Result.Error -> _registerState.emit(DataState.Error(result.exception.message))
                }
            }
    }
}