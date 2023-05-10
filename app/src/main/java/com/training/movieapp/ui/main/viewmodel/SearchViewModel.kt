package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.movie.SearchMoviesUseCase
import com.training.movieapp.domain.usecase.people.SearchPeoplesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val searchPeoplesUseCase: SearchPeoplesUseCase
) : ViewModel() {

    private val _searchMoviesState = MutableStateFlow<DataState<PageResponse<Movie>>>(DataState.Idle)
    val searchMoviesState: StateFlow<DataState<PageResponse<Movie>>> = _searchMoviesState.asStateFlow()

    private val _searchPeoplesState = MutableStateFlow<DataState<PageResponse<People>>>(DataState.Idle)
    val searchPeoplesState: StateFlow<DataState<PageResponse<People>>> = _searchPeoplesState.asStateFlow()

    fun searchMovies(query: String, page: Int = 1) = viewModelScope.launch {
        searchMoviesUseCase.execute(query, page)
            .onStart { _searchMoviesState.emit(DataState.Loading) }
            .collect() { result ->
                when (result) {
                    is Result.Success -> {
                        _searchMoviesState.emit(DataState.Success(result.data))
                    }

                    is Result.Error -> {
                        _searchMoviesState.emit(DataState.Error(result.exception.message))
                    }
                }
            }
    }

    fun searchPeoples(query: String, page: Int = 1) = viewModelScope.launch {
        searchPeoplesUseCase.execute(query, page)
            .onStart { _searchPeoplesState.emit(DataState.Loading) }
            .collect() { result ->
                when (result) {
                    is Result.Success -> {
                        _searchPeoplesState.emit(DataState.Success(result.data))
                    }

                    is Result.Error -> {
                        _searchPeoplesState.emit(DataState.Error(result.exception.message))
                    }
                }
            }
    }
}
