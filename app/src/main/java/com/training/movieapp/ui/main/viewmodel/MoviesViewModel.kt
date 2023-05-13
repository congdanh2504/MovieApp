package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.Movies
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.movie.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    BaseViewModel() {
    private val _moviesState = MutableStateFlow<DataState<Movies>>(DataState.Idle)
    val moviesState: StateFlow<DataState<Movies>> = _moviesState.asStateFlow()

    fun getMovies() = viewModelScope.launch {
        handleState(_moviesState, getMoviesUseCase.execute())
    }
}
