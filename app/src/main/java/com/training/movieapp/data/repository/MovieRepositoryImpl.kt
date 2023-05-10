package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.TheMovieDbApi
import com.training.movieapp.domain.model.MovieDetail
import com.training.movieapp.domain.model.Movies
import com.training.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val theMovieDbApi: TheMovieDbApi) :
    MovieRepository {
    override suspend fun getMovieDetail(movieId: Int) = flow {
        try {
            val movie = theMovieDbApi.getMovie(movieId)
            val credit = theMovieDbApi.getMovieCredit(movieId)
            val similar = theMovieDbApi.getSimilarMovies(movieId)
            emit(Result.Success(MovieDetail(movie, credit, similar)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun getMovies() = flow {
        try {
            val movieTrending = theMovieDbApi.getMovieTrending()
            val movieNowPlaying = theMovieDbApi.getMovieNowPlaying()
            val movieTopRated = theMovieDbApi.getMovieTopRated()
            val movieUpcoming = theMovieDbApi.getMovieUpcoming()
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
            val movies = theMovieDbApi.searchMovies(query, page)
            emit(Result.Success(movies))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
