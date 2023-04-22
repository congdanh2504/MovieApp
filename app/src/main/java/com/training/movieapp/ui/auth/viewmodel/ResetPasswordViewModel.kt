package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.domain.usecase.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val resetPasswordUseCase: ResetPasswordUseCase) :
    ViewModel() {
    private val _resetPasswordState = MutableSharedFlow<OperationState<Unit>>(replay = 0)
    val resetPasswordState: SharedFlow<OperationState<Unit>> = _resetPasswordState.asSharedFlow()

    fun resetPassword(email: String) = viewModelScope.launch {
        resetPasswordUseCase.resetPassword(email)
            .onStart { _resetPasswordState.emit(OperationState.Loading) }
            .collect { result ->
                when (result) {
                    is Result.Success -> _resetPasswordState.emit(OperationState.Success(Unit))
                    is Result.Error -> _resetPasswordState.emit(OperationState.Error(result.exception.message))
                }
            }
    }
}