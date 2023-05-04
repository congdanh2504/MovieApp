package com.training.movieapp.data.usecase.movie

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.repository.MovieRepository
import com.training.movieapp.domain.usecase.movie.GetTrendingMovieUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingMovieUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository) :
    GetTrendingMovieUseCase {
    override suspend fun execute(): Flow<Result<PageResponse<Movie>>> {
        return  movieRepository.getMovieTrending()
    }
}