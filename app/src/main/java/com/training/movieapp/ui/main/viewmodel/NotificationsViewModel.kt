package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.user.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel@Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    BaseViewModel()  {
    private val _usersState =
        MutableStateFlow<DataState<List<User>>>(DataState.Idle)
    val usersState: StateFlow<DataState<List<User>>> =
        _usersState.asStateFlow()
    fun getUsers() = viewModelScope.launch {
        handleState(_usersState,getUsersUseCase.execute())
    }
}