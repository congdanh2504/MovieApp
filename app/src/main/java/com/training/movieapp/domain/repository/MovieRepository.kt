package com.training.movieapp.domain.repository

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.MovieDetail
import com.training.movieapp.domain.model.PageResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieDetail(movieId: Int): Flow<Result<MovieDetail>>
    suspend fun getMovieTrending(): Flow<Result<PageResponse<Movie>>>
    suspend fun searchMovies(query: String, page: Int): Flow<Result<PageResponse<Movie>>>
}
