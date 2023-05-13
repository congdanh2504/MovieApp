package com.training.movieapp.ui.settings.viewmodel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import com.training.movieapp.domain.usecase.datastore.SaveUserUseCase
import com.training.movieapp.domain.usecase.user.UpdateProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val readUserUseCase: ReadUserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val saveUserUseCase: SaveUserUseCase
) :
    BaseViewModel() {

    private val _updateProfileState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val updateProfileState: StateFlow<DataState<User>> = _updateProfileState.asStateFlow()

    private val _userState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val userState: StateFlow<DataState<User>> = _userState.asStateFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        handleState(_userState, readUserUseCase.execute())
    }

    fun updateProfile(username: String, bio: String, imageUri: Uri?) = viewModelScope.launch {
        handleState(_updateProfileState, updateProfileUseCase.execute(username, bio, imageUri))
    }

    fun saveUser(user: User) = viewModelScope.launch {
        saveUserUseCase.execute(user)
    }
}
