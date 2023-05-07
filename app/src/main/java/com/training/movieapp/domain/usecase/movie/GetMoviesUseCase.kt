package com.training.movieapp.domain.usecase.movie

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend fun execute(): Flow<Result<Movies>>
}
