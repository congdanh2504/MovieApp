package com.training.movieapp.ui.detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.usecase.movie.GetDetailMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val getDetailMovieUseCase: GetDetailMovieUseCase) :
    ViewModel() {

    fun getDetailMovie(movieId: Int) = viewModelScope.launch {
        getDetailMovieUseCase.execute(movieId).collect { result ->
            when (result) {
                is Result.Success -> {
                    Log.d("AAA", result.data.toString())
                }

                is Result.Error -> {
                    Log.d("AAA", result.exception.message.toString())
                }
            }
        }
    }
}