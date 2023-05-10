package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieCredit(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("poster_path") val posterPath: String?
)
