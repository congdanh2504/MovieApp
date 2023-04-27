package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("budget") val budget: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<Company>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("title") val title: String,
    @SerializedName("status") val status: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)
