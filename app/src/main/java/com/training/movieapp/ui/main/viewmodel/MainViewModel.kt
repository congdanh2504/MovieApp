package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import com.training.movieapp.domain.usecase.user.CheckUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readUserUseCase: ReadUserUseCase,
    private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase
) :
    BaseViewModel() {
    private val _userState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val userState: StateFlow<DataState<User>> = _userState.asStateFlow()

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

    fun readUser() = viewModelScope.launch {
        handleState(_userState, readUserUseCase.execute())
    }

    fun checkUserLoggedIn() = viewModelScope.launch {
        checkUserLoggedInUseCase.execute().collect { isUserLoggedIn ->
            _isUserLoggedIn.value = isUserLoggedIn
        }
    }
}
