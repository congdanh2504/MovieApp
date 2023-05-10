package com.training.movieapp.domain.model

data class Movies(
    val trendingMovies: PageResponse<Movie>,
    val nowPlayingMovies: PageResponse<Movie>,
    val topRatedMovies: PageResponse<Movie>,
    val upComingMovies: PageResponse<Movie>
)
