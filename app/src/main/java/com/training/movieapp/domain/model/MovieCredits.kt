package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieCredits(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<MovieCredit>,
    @SerializedName("crew") val crew: List<MovieCredit>
)