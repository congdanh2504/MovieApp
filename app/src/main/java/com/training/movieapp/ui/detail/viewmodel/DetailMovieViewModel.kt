package com.training.movieapp.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Credit
import com.training.movieapp.domain.model.MovieDetail
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.movie.GetDetailMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val getDetailMovieUseCase: GetDetailMovieUseCase) :
    ViewModel() {

    private val _movieState = MutableStateFlow<DataState<MovieDetail>>(DataState.Idle)
    val movieDetailState: StateFlow<DataState<MovieDetail>> = _movieState.asStateFlow()

    fun getDetailMovie(movieId: Int) = viewModelScope.launch {
        getDetailMovieUseCase.execute(movieId)
            .onStart { _movieState.emit(DataState.Loading) }
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        _movieState.emit(DataState.Success(result.data))
                    }

                    is Result.Error -> {
                        _movieState.emit(DataState.Error(result.exception.message))
                    }
                }
            }
    }
}