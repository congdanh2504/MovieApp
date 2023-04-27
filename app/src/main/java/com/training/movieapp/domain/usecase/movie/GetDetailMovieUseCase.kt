package com.training.movieapp.domain.usecase.movie

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface GetDetailMovieUseCase {
    suspend fun execute(movieId: Int): Flow<Result<MovieDetail>>
}