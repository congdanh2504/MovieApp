package com.training.movieapp.data.usecase.movie

import com.training.movieapp.domain.repository.MovieRepository
import com.training.movieapp.domain.usecase.movie.SearchMoviesUseCase
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository) :
    SearchMoviesUseCase {
    override suspend fun execute(query: String, page: Int) =
        movieRepository.searchMovies(query, page)
}
