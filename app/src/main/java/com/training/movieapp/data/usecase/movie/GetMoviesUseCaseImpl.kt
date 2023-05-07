package com.training.movieapp.data.usecase.movie

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Movies
import com.training.movieapp.domain.repository.MovieRepository
import com.training.movieapp.domain.usecase.movie.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository) :
    GetMoviesUseCase {
    override suspend fun execute(): Flow<Result<Movies>> {
        return movieRepository.getMovies()
    }
}
