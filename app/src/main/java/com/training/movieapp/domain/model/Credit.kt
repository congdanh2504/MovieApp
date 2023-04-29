package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("id") val movieId: Int,
    @SerializedName("cast") val casts: List<Cast>,
    @SerializedName("crew") val crews: List<Crew>
)