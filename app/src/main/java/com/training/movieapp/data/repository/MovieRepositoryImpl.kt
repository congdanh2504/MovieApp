package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.MovieApi
import com.training.movieapp.domain.model.MovieDetail
import com.training.movieapp.domain.model.Movies
import com.training.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieApi) :
    MovieRepository {
    override suspend fun getMovieDetail(movieId: Int) = flow {
        try {
            val movie = movieApi.getMovie(movieId)
            val credit = movieApi.getMovieCredit(movieId)
            val similar = movieApi.getSimilarMovies(movieId)
            emit(Result.Success(MovieDetail(movie, credit, similar)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun getMovies() = flow {
        try {
            val movieTrending = movieApi.getMovieTrending()
            val movieNowPlaying = movieApi.getMovieNowPlaying()
            val movieTopRated = movieApi.getMovieTopRated()
            val movieUpcoming = movieApi.getMovieUpcoming()
            emit(
                Result.Success(
                    Movies(
                        movieTrending,
                        movieNowPlaying,
                        movieTopRated,
                        movieUpcoming
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun searchMovies(query: String, page: Int) = flow {
        try {
            val movies = movieApi.searchMovies(query, page)
            emit(Result.Success(movies))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
