package com.training.movieapp.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.domain.usecase.user.CheckUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase) :
    ViewModel() {
    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

    fun checkUserLoggedIn() = viewModelScope.launch {
        checkUserLoggedInUseCase.execute().collect { isUserLoggedIn ->
            _isUserLoggedIn.value = isUserLoggedIn
        }
    }
}