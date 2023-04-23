package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.domain.usecase.auth.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val resetPasswordUseCase: ResetPasswordUseCase) :
    ViewModel() {
    private val _resetPasswordState = MutableStateFlow<OperationState<Unit>>(OperationState.Idle)
    val resetPasswordState: StateFlow<OperationState<Unit>> = _resetPasswordState.asStateFlow()

    fun resetPassword(email: String) = viewModelScope.launch {
        resetPasswordUseCase.execute(email)
            .onStart { _resetPasswordState.emit(OperationState.Loading) }
            .collect { result ->
                when (result) {
                    is Result.Success -> _resetPasswordState.emit(OperationState.Success(Unit))
                    is Result.Error -> _resetPasswordState.emit(OperationState.Error(result.exception.message))
                }
            }
    }
}