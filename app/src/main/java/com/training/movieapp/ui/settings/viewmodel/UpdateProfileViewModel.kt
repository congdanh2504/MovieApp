package com.training.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.usecase.ReadUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(private val readUserUseCase: ReadUserUseCase) :
    ViewModel() {

    private val _user = MutableSharedFlow<User>(replay = 0)
    val user: SharedFlow<User> = _user.asSharedFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        readUserUseCase.readUser().collect() { user ->
            _user.emit(user)
        }
    }
}