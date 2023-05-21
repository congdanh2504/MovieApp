package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.people.GetPeoplePopularUseCase
import com.training.movieapp.domain.usecase.user.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getPeoplePopularUseCase: GetPeoplePopularUseCase,
    private val getUsersUseCase: GetUsersUseCase
) :
    BaseViewModel() {
    private val _peoplePopularState =
        MutableStateFlow<DataState<PageResponse<People>>>(DataState.Idle)
    val peoplePopularState: StateFlow<DataState<PageResponse<People>>> =
        _peoplePopularState.asStateFlow()
    private val _usersState =
        MutableStateFlow<DataState<List<User>>>(DataState.Idle)
    val usersState: StateFlow<DataState<List<User>>> =
        _usersState.asStateFlow()

    fun getPeoplePopular() = viewModelScope.launch {
        handleState(_peoplePopularState, getPeoplePopularUseCase.execute())
    }

    fun getUsers() = viewModelScope.launch {
        handleState(_usersState, getUsersUseCase.execute())
    }
}
