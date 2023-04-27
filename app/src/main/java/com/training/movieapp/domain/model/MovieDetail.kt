package com.training.movieapp.domain.model

data class MovieDetail(
    val id: Int,
    val backdropPath: String?,
    val budget: String,
    val genres: List<Genre>,
    val homepage: String?,
    val originalTitle: String,
    val originalLanguage: String,
    val overview: String?,
    val popularity: String?,
    val posterPath: String?,
    val productionCompanies: List<Company>,
    val releaseDate: String,
    val runtime: Int?,
    val title: String,
    val status: String,
    val voteAverage: Int,
    val voteCount: Int
)
