package com.training.movieapp.ui.settings.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.UpdateProfileState
import com.training.movieapp.domain.usecase.ReadUserUseCase
import com.training.movieapp.domain.usecase.SaveUserUseCase
import com.training.movieapp.domain.usecase.UpdateProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val readUserUseCase: ReadUserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val saveUserUseCase: SaveUserUseCase
) :
    ViewModel() {

    private val _user = MutableSharedFlow<User>(replay = 0)
    val user: SharedFlow<User> = _user.asSharedFlow()

    private val _updateProfileState = MutableSharedFlow<UpdateProfileState>(replay = 0)
    val updateProfileState: SharedFlow<UpdateProfileState> = _updateProfileState.asSharedFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        readUserUseCase.readUser().collect() { user ->
            _user.emit(user)
        }
    }

    fun updateProfile(username: String, bio: String, imageUri: Uri?) = viewModelScope.launch {
        updateProfileUseCase.updateProfile(username, bio, imageUri)
            .onStart { _updateProfileState.emit(UpdateProfileState.Loading) }
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        _updateProfileState.emit(UpdateProfileState.Success(result.data))
                    }
                    is Result.Error -> {
                        _updateProfileState.emit(UpdateProfileState.Error(result.exception.message))
                    }
                }
            }
    }

    fun saveUser(user: User) = viewModelScope.launch {
        saveUserUseCase.saveUser(user)
    }
}