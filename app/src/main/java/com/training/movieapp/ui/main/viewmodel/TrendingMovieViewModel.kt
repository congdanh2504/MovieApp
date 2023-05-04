package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.movie.GetTrendingMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingMovieViewModel @Inject constructor(private val getTrendingMovieUseCase: GetTrendingMovieUseCase) :
    ViewModel() {
    private val _movieTrendingState = MutableStateFlow<DataState<PageResponse<Movie>>>(DataState.Idle)
    val movieTrendingState: StateFlow<DataState<PageResponse<Movie>>> = _movieTrendingState

    fun getTrendingMovie() = viewModelScope.launch {
        getTrendingMovieUseCase.execute()
            .onStart { _movieTrendingState.emit(DataState.Loading) }
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        _movieTrendingState.emit(DataState.Success(result.data))
                    }
                    is Result.Error -> {
                        _movieTrendingState.emit(DataState.Error(result.exception.message))
                    }
                }
            }
    }
}
