package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.state.ResetPasswordState
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
    private val _resetPasswordState = MutableSharedFlow<ResetPasswordState>(replay = 0)
    val resetPasswordState: SharedFlow<ResetPasswordState> = _resetPasswordState.asSharedFlow()

    fun resetPassword(email: String) = viewModelScope.launch {
        resetPasswordUseCase.resetPassword(email)
            .onStart { _resetPasswordState.emit(ResetPasswordState.Loading) }
            .collect { result ->
                when (result) {
                    is Result.Success -> _resetPasswordState.emit(ResetPasswordState.EmailSent)
                    is Result.Error -> _resetPasswordState.emit(ResetPasswordState.Error(result.exception.message))
                }
            }
    }
}