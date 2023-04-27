package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.TheMovieDbApi
import com.training.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val theMovieDbApi: TheMovieDbApi) :
    MovieRepository {
    override suspend fun getMovieDetail(movieId: Int) = flow {
        try {
            val movieDetail = theMovieDbApi.getMovieDetail(movieId)
            emit(Result.Success(movieDetail))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}