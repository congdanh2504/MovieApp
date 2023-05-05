package com.training.movieapp.domain.usecase.movie

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.PageResponse
import kotlinx.coroutines.flow.Flow

interface GetTrendingMovieUseCase {
    suspend fun execute(): Flow<Result<PageResponse<Movie>>>
}
