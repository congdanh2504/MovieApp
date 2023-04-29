package com.training.movieapp.data.usecase.movie

import com.training.movieapp.domain.repository.MovieRepository
import com.training.movieapp.domain.usecase.movie.GetDetailMovieUseCase
import javax.inject.Inject

class GetDetailMovieUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository) :
    GetDetailMovieUseCase {
    override suspend fun execute(movieId: Int) = movieRepository.getMovieDetail(movieId)
}