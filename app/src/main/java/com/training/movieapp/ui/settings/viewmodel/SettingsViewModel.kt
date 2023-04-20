package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.SignOutState
import com.training.movieapp.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val signOutUseCase: SignOutUseCase) :
    ViewModel() {
    private val _signOutState = MutableSharedFlow<SignOutState>(replay = 0)
    val signOutState: SharedFlow<SignOutState> = _signOutState.asSharedFlow()

    fun signOut() = viewModelScope.launch {
        signOutUseCase.signOut()
            .collect { result ->
                when (result) {
                    is Result.Success -> _signOutState.emit(SignOutState.Success)
                    is Result.Error -> _signOutState.emit(SignOutState.Error(result.exception.message))
                }
            }
    }
}