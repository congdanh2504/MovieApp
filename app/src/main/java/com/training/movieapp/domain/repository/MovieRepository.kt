package com.training.movieapp.domain.repository

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Credit
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieDetail(movieId: Int): Flow<Result<MovieDetail>>
    suspend fun getMovieCredit(movieId: Int): Flow<Result<Credit>>
}