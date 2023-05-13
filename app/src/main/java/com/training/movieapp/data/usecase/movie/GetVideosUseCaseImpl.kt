package com.training.movieapp.data.usecase.movie

import com.training.movieapp.domain.repository.MovieRepository
import com.training.movieapp.domain.usecase.movie.GetVideosUseCase
import javax.inject.Inject

class GetVideosUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository) :
    GetVideosUseCase {
    override suspend fun execute(movieId: Int) = movieRepository.getVideos(movieId)
}