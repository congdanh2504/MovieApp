package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Movies
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.movie.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel@Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {
    private val _moviesState = MutableStateFlow<DataState<Movies>>(DataState.Idle)
    val moviesState: StateFlow<DataState<Movies>> = _moviesState.asStateFlow()

    fun getMovies() = viewModelScope.launch {
        getMoviesUseCase.execute()
            .onStart { _moviesState.emit(DataState.Loading) }
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        _moviesState.emit(DataState.Success(result.data))
                    }
                    is Result.Error -> {
                        _moviesState.emit(DataState.Error(result.exception.message))
                    }
                }
            }
    }
}
