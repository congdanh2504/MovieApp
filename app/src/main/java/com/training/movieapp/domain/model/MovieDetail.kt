package com.training.movieapp.domain.model

data class MovieDetail(
    val movie: Movie,
    val credit: Credit,
    val similar: PageResponse<Movie>
)
