package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val readUserUseCase: ReadUserUseCase) :
    ViewModel() {
    private val _userState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val userState: StateFlow<DataState<User>> = _userState.asStateFlow()

    init {
        readUser()
    }

    private fun readUser() = viewModelScope.launch {
        readUserUseCase.execute()
            .onStart { _userState.emit(DataState.Loading) }
            .collect() { result ->
                when (result) {
                    is Result.Success -> {
                        _userState.emit(DataState.Success(result.data))
                    }

                    is Result.Error -> {
                        _userState.emit(DataState.Error(result.exception.message))
                    }
                }
            }
    }
}