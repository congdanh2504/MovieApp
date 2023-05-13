package com.training.movieapp.ui.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.MovieDetail
import com.training.movieapp.domain.model.VideoResponse
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.movie.GetDetailMovieUseCase
import com.training.movieapp.domain.usecase.movie.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getVideosUseCase: GetVideosUseCase
) :
    BaseViewModel() {

    private val _movieState = MutableStateFlow<DataState<MovieDetail>>(DataState.Idle)
    val movieDetailState: StateFlow<DataState<MovieDetail>> = _movieState.asStateFlow()

    private val _videosState = MutableStateFlow<DataState<VideoResponse>>(DataState.Idle)
    val videosState: StateFlow<DataState<VideoResponse>> = _videosState.asStateFlow()

    fun getDetailMovie(movieId: Int) = viewModelScope.launch {
        handleState(_movieState, getDetailMovieUseCase.execute(movieId))
    }

    fun getVideos(movieId: Int) = viewModelScope.launch {
        handleState(_videosState, getVideosUseCase.execute(movieId))
    }

}
